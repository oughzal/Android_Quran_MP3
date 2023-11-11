package com.omarcomputer.quran.util

import android.content.Context

class FilerHelper {
    companion object {
        fun getContentFromRessource(context: Context, ressourceId: Int): String {
            return context.resources.openRawResource(ressourceId).use { stream ->
                stream.bufferedReader().use {
                    it.readText()
                }
            }
        }

        fun getContentFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use { stream ->
                stream.bufferedReader().use {
                    it.readText()
                }
            }
        }
    }
}