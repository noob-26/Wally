package com.example.wally

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api")
    fun getImages(@Query("key") key: String, @Query("q") category: String): Call<ApiModel>
}