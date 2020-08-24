package com.example.test.helpers

import android.content.Context
import android.graphics.Typeface
import java.lang.Exception
import java.util.*

object TypeFaceProvider {

    private val cache: Hashtable<String, Typeface> = Hashtable()

    fun getFontForTextView(cxt: Context, name: String): Typeface {
        synchronized(cache)
        {
            if (!cache.containsKey(name)) {
                try {
                    val t = Typeface.createFromAsset(cxt.assets, name)
                    cache[name] = t
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return cache[name]!!
        }
    }
}