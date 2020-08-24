package com.example.test.data.api

import androidx.collection.ArrayMap
import com.example.test.data.models.SearchResultsModel
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

//    @Headers("Content-Type: application/json")
//    @POST("/api/v1/create")
//    @FormUrlEncoded
//    fun getDataList(@Field("name") name: String, @Field("salary") salary: String, @Field("age") age: Int): Call<DataList>
//      fun getLogsList(@Body rawString: LogsPost): Call<Data>
//
//    @Headers("Content-Type: application/json")
//    @POST("store-list")
//    @FormUrlEncoded
//    fun getStoresAllList(@Body json: RequestBody): Call<Store>
//
//    @Headers("Content-Type: application/json")
//    @GET("/api/users?")
//    fun getTodosList(): Call<DataList>

//    @Headers("Content-Type: application/json")
    @POST
    @FormUrlEncoded
    fun getDataList(@Url url: String, @FieldMap paramMap: ArrayMap<String, String>): Call<SearchResultsModel>

}