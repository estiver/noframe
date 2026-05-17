package com.eh2.noframe.presentation.dto.book

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class BookResponseDTO @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) constructor(
    @param:JsonProperty("id") val id: String?,
    @param:JsonProperty("title") val title: String
)
