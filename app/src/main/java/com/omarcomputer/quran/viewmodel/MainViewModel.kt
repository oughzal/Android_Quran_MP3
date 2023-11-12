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
import kotlinx.coroutines.coroutineScope
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
    var currentSorat = MutableLiveData<Sorat>()
    var currentAyat = MutableLiveData<Ayat>()
    var index = MutableLiveData<Int>(0)

     fun getSowar(){
         CoroutineScope(Dispatchers.IO).launch {
             val data  = soratDao.getAllSorat()
             sowar.postValue(data)
             currentSorat.postValue(data[0])
             getAyat(data[0])
         }

    }
    fun getAyat(sorat: Sorat){

        CoroutineScope(Dispatchers.IO).launch {
            currentSorat.postValue(sorat)
            val data  = soratDao.getAllAyat(sorat.id)
            index.postValue(0)
            currentAyat.postValue(data[0])
            ayat.postValue(data)
        }

    }

    fun nextAya(){
        if(index.value!! < (ayat.value?.size ?: -1) -1 ){
            index.value = index.value!! +1
            currentAyat.value = ayat.value!![index.value!!]
        }
    }



}