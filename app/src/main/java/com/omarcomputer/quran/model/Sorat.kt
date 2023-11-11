package com.omarcomputer.quran.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sorat(
    val id : String,
    val name : String
)