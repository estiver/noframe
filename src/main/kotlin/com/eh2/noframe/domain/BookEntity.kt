package com.eh2.noframe.domain

class BookEntity(
    val id: String?,
    val title: String
) : Cloneable {

    constructor(title: String) : this(null, title)

    @Throws(CloneNotSupportedException::class)
    fun generateWithId(id: String): BookEntity {
        return BookEntity(id, title)
    }
}
