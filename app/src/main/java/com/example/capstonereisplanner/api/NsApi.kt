package com.example.capstonereisplanner.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NsApi {
    companion object{
        private const val baseUrl = "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v2/stations/"

        fun createApi(): NsApiService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor{addApiKeyInterceptor(it)}
                .build()

            val nsApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return nsApi.create(NsApiService::class.java)
        }

        private fun addApiKeyInterceptor(it: Interceptor.Chain):Response{
            val originalRequest = it.request()

            val requestWithApiKey = originalRequest.newBuilder()
                .addHeader("Ocp-Apim-Subscription-Key","2eaca8fe3e7d44108b39a138d13ad9c8")
                .url(originalRequest.url)
                .build()

            return it.proceed(request = requestWithApiKey)
        }
    }
}