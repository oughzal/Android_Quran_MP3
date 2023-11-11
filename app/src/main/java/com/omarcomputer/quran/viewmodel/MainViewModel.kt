package com.omarcomputer.quran.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omarcomputer.quran.R
import com.omarcomputer.quran.model.Sorat
import com.omarcomputer.quran.util.FilerHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class MainViewModel(val app : Application) : AndroidViewModel(app) {
    val listType = Types.newParameterizedType(List::class.java, Sorat::class.java)

    init {
        getSowar()
    }
    val text =""
    var sowar = MutableLiveData<List<Sorat>>()
    var ayat = MutableLiveData<List<Sorat>>()
    var current = MutableLiveData<Sorat>()

     fun getSowar(){
         val text = FilerHelper.getContentFromAssets(app.applicationContext,"list.json")
         Log.i("QuranTag",text)
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

         val adapter : JsonAdapter<List<Sorat>> = moshi.adapter(listType)

        val data = adapter.fromJson(text)
        Log.i("QuranTag",data?.size.toString()?:"")
        sowar.value = data
    }
}