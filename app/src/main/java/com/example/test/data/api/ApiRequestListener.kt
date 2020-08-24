package com.example.test.data.api

import retrofit2.Response

interface ApiRequestListener {
    fun onReceiveResult(resquest: String, response: Response<*>)
    fun onReceiveError(resquest: String, error: String)

}