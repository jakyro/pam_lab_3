package com.example.lab2.request

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {
    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY);
    // maybe later :)
//    private val authInterceptor = Interceptor { chain ->
//        val newRequest = chain.request()
//            .newBuilder()
//            .url("")
//            .build()
//
//        chain.proceed(newRequest)
//    }

    private val client = OkHttpClient().newBuilder()
//        .addInterceptor(authInterceptor)
        .addInterceptor(interceptor)
        .build()
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://eucc.me/v1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val movieService: MovieService = retrofit().create(MovieService::class.java)

}
