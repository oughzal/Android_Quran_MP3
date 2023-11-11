package com.omarcomputer.quran.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omarcomputer.quran.model.Ayat
import com.omarcomputer.quran.model.Sorat

@Database(entities = [Sorat::class,Ayat::class],version=2, exportSchema = false)
abstract class QuranDatabase : RoomDatabase() {
    abstract fun soratDao(): SoratDao

    companion object{
        @Volatile
        private var INSTANCE :QuranDatabase?=null

        fun getDatabase(context: Context):QuranDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuranDatabase::class.java,
                        "quranDatabase.db"
                    )
                        .createFromAsset("quranDatabase.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!;
        }
    }
}