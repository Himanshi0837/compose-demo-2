package com.example.bledemo.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://staging.blehealthplus.com/reelcache/public/api/v1/"

    const val DEFAULT_TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmcuYmxlaGVhbHRocGx1cy5jb20vcmVlbGNhY2hlL3B1YmxpYy9hcGkvdjEvbG9naW4iLCJpYXQiOjE3OTM4NzMzMDQsImV4cCI6MTc5NjQ2NTMwNCwibmJmIjoxNzkzODczMzA0LCJqdGkiOiJLMVJUZEhvaGJRU1hqbkNyIiwic3ViIjoiMjkiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwidG9rZW5fdmVyc2lvbiI6NTB9.8EyKObJ1aQPxC9uUxVOMCObGzJUpEH5B4mXiqnOMRG4"

    /**
     * Authorization token used for authenticated endpoints.
     * Initialized with the default user token.
     */
    var authToken: String? = DEFAULT_TOKEN

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
                .header("Accept", "application/json")

            authToken?.takeIf { it.isNotBlank() }?.let { token ->
                val authHeader = if (token.startsWith("Bearer ")) token else "Bearer $token"
                requestBuilder.header("Authorization", authHeader)
            }
            chain.proceed(requestBuilder.build())
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
