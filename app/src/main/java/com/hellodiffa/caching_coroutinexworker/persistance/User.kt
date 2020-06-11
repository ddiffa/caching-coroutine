package com.hellodiffa.caching_coroutinexworker.persistance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val image: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val email: String,
    val username: String
)