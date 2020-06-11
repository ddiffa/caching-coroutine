package com.hellodiffa.caching_coroutinexworker.repository

import com.hellodiffa.caching_coroutinexworker.network.UserApi
import com.hellodiffa.caching_coroutinexworker.persistance.User
import com.hellodiffa.caching_coroutinexworker.persistance.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    suspend fun getAllUser() = userDao.loadAllUser()
    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            val data = UserApi.api.getListUser()

            userDao.insertUser(data.users.map {
                User(
                    image = it.image,
                    username = it.username,
                    email = it.email,
                    id = it.id
                )
            })
        }
    }
}