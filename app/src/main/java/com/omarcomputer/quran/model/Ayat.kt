package com.omarcomputer.quran.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ayat",
    foreignKeys = [ForeignKey(
        entity = Sorat::class,
        parentColumns = ["id"],
        childColumns = ["sorat"]
    )]
)
data class Ayat(
    @PrimaryKey val id: String,
    val sorat: String,
    val text: String?
)