package com.hellodiffa.caching_coroutinexworker.network

import com.squareup.moshi.Json


data class UsersItem(

    @Json(name="image")
    val image: String,

    @Json(name="id")
    val id: Int,

    @Json(name="email")
    val email: String,

    @Json(name="username")
    val username: String
)
