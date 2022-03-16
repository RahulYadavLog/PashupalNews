package com.mindfire.newstask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceClass {
    companion object{
        val BaseUrl="https://newsapi.org/v2/"
        fun getRetrofitInstance(): Retrofit
        {
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}