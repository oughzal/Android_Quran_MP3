package com.omarcomputer.quran.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "ayat",foreignKeys = [ForeignKey(entity = Sorat::class,
    parentColumns = ["id"],
    childColumns = ["sorat"],
    onDelete = ForeignKey.CASCADE)])
data class Ayat (
    val id : String,
    val text : String,
    val sorat : String
        )