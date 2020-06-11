package com.hellodiffa.caching_coroutinexworker.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://my-json-server.typicode.com/ddiffa/")
    .build()


interface ApiService {
    @GET("caching-coroutinexworker/db")
    suspend fun getListUser(): Response
}

object UserApi {
    val api = retrofit.create(ApiService::class.java)
}