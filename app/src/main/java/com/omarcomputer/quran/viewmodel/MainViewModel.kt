package com.omarcomputer.quran.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omarcomputer.quran.R
import com.omarcomputer.quran.data.QuranDatabase
import com.omarcomputer.quran.model.Ayat
import com.omarcomputer.quran.model.Sorat
import com.omarcomputer.quran.util.FilerHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class MainViewModel(val app : Application) : AndroidViewModel(app) {
    private val quranDatabase =QuranDatabase.getDatabase(app)
    private val soratDao = quranDatabase.soratDao()
       init {

    }
    val text =""
    var sowar = MutableLiveData<List<Sorat>>()
    var ayat = MutableLiveData<List<Ayat>>()
    var current = MutableLiveData<Sorat>()

     fun getSowar(){
            val data  = soratDao.getAllSorat()
             sowar.value = data
    }

}