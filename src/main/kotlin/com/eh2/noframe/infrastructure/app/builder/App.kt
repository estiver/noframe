package com.eh2.noframe.infrastructure.app.builder

import com.eh2.noframe.infrastructure.Config
import com.eh2.noframe.infrastructure.http.ContextHttpHandlers
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.net.InetSocketAddress
import java.util.concurrent.Executors

class App private constructor(builder: AppBuilder) {
    private val httpServer: HttpServer = requireNotNull(builder.configuredHttpServer)

    fun start() {
        httpServer.start()
    }

    class AppBuilder(
        private val config: Config
    ) {
        internal var configuredHttpServer: HttpServer? = null

        @Throws(IOException::class)
        fun httpServer(): AppBuilder {
            val server = HttpServer.create(InetSocketAddress(config.getHttpserverPort()), 0)
            server.executor = Executors.newFixedThreadPool(config.getHttpServerExecutorNThreads())

            configuredHttpServer = server
            return this
        }

        fun contexts(): AppBuilder {
            val contextHandlersMap: Map<String, HttpHandler> = requireNotNull(
                ContextHttpHandlers().buildContextHandlresMap().contextHandlresMap
            )
            for ((key, value) in contextHandlersMap) {
                configuredHttpServer?.createContext(key, value)
            }
            return this
        }

        fun build(): App {
            return App(this)
        }
    }
}
