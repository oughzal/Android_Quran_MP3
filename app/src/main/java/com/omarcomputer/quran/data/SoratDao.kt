package com.omarcomputer.quran.data

import androidx.room.Dao
import androidx.room.Query
import com.omarcomputer.quran.model.Ayat
import com.omarcomputer.quran.model.Sorat

@Dao
abstract interface SoratDao {

    @Query("SELECT * FROM Sorat")
    fun getAllSorat(): List<Sorat>

    @Query("SELECT * from AYAT where sorat=:soratId")
    fun getAllAyat(soratId : String):List<Ayat>
}