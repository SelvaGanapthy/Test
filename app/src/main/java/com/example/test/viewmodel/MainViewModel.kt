package com.example.test.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleObserver
import com.example.test.data.api.ApiRequestListener
import com.example.test.data.api.ApiServices
import com.example.test.data.api.RetrofitConnect
import com.example.test.data.models.ErrorHandler
import com.example.test.data.models.ProgressHandler
import com.example.test.data.models.SearchResultsModel
import com.example.test.utils.Constants
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import java.util.*

class MainViewModel : Observable(), ApiRequestListener, LifecycleObserver {

    private lateinit var apiType: String
    internal var apiServices: ApiServices? = null
    internal var retrofitConnect: RetrofitConnect? = null
    lateinit var ReqType: String

    init {
        retrofitConnect = RetrofitConnect()
        apiServices = retrofitConnect?.retrofit(Constants.BASE_URL!!)
    }

    fun onClick(v: View) {
        setChanged()
        notifyObservers(v)
    }

    /*Retrofit Call*/
    fun matchProfileApiCall(ReqType: String, params: androidx.collection.ArrayMap<String, String>) {
        val retroReq: Call<SearchResultsModel> =
            apiServices?.getDataList("3/1", params) as Call<SearchResultsModel>
        this.ReqType = ReqType
        retrofitConnect?.AddToEnqueue(retroReq, this, ReqType)

        Log.i("params", params.toString())

        /*Progress Enable*/
        setChanged()
        notifyObservers(ProgressHandler(true, "processing to fetch data..."))
    }

    /*Retrofit Response*/
    override fun onReceiveResult(resquest: String, response: Response<*>) {
        try {

            /*Progress Disable*/
            setChanged()
            notifyObservers(ProgressHandler(false, ""))

            setChanged()
            notifyObservers(response.body() as SearchResultsModel)
            Log.i("res", response.isSuccessful.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /*Retrofit Failer*/
    override fun onReceiveError(resquest: String, error: String) {
        try {
            /*Progress Disable*/
            setChanged()
            notifyObservers(ProgressHandler(false, ""))

            setChanged()
            notifyObservers(ErrorHandler(resquest, error))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}