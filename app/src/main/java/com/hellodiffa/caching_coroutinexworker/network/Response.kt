package com.hellodiffa.caching_coroutinexworker.network

import com.squareup.moshi.Json

data class Response(
	@Json(name="users")
	val users: List<UsersItem>
)
