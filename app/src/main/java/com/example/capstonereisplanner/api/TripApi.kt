package com.example.capstonereisplanner.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TripApi {
    companion object {
        private const val tripUrl =
            "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips/"

        fun createApi(): TripApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .addInterceptor { addApiKeyInterceptor(it) }
                .build()

            val tripApi = Retrofit.Builder()
                .baseUrl(tripUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return tripApi.create(TripApiService::class.java)
        }

        private fun addApiKeyInterceptor(it: Interceptor.Chain): Response {
            val originalRequest = it.request()

            val requestWithApiKey = originalRequest.newBuilder()
                .addHeader("Ocp-Apim-Subscription-Key", "2eaca8fe3e7d44108b39a138d13ad9c8")
                .url(originalRequest.url)
                .build()

            return it.proceed(request = requestWithApiKey)
        }
    }
}