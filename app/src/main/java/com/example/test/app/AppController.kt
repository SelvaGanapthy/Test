package com.example.test.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.test.view.activities.MainActivity

class AppController : Application() {

    //Static  methods & variable
    companion object {
        val TAG = AppController::class.java.simpleName
        @SuppressLint("StaticFieldLeak")
        @get:Synchronized
        var mainActivity: MainActivity? = null
        @SuppressLint("StaticFieldLeak")
        var instance: AppController? = null
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }

    override fun onCreate() {

        try {
            super.onCreate()
            instance = this
            context = this.applicationContext

        } catch (e: Exception) {

        }
    }


    //Instance creation for all Activity
    @Synchronized
    public fun getInstance(): AppController = instance!!

}