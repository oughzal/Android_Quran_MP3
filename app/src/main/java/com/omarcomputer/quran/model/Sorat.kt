package com.omarcomputer.quran.model

import androidx.room.Entity

@Entity(tableName = "sorat")
data class Sorat(
    val id : String,
    val name : String
)