package com.mindfire.newstask.network

import com.mindfire.newstask.model.RecyclerList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("top-headlines")
suspend fun getDataFromApi(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") query: String?,
        @Query("apiKey") apiKey: String
    ): RecyclerList
}