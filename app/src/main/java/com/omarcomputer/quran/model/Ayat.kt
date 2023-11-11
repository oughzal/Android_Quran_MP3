package com.omarcomputer.quran.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ayat (
    val id : String,
    val text : String
        )