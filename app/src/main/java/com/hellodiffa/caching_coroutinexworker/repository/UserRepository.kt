package com.hellodiffa.caching_coroutinexworker.repository

import androidx.work.*
import com.hellodiffa.caching_coroutinexworker.network.UserApi
import com.hellodiffa.caching_coroutinexworker.persistance.User
import com.hellodiffa.caching_coroutinexworker.persistance.UserDao
import com.hellodiffa.caching_coroutinexworker.worker.UserWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class UserRepository(private val userDao: UserDao) {

    init {
        refreshDataUsingWork()
    }

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

    private fun refreshDataUsingWork() {
        CoroutineScope(Dispatchers.Default).launch {
            val constraints = Constraints.Builder()
                .setRequiresStorageNotLow(true)
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workManager = WorkManager.getInstance()

            val request: WorkRequest =
                PeriodicWorkRequestBuilder<UserWorker>(6, TimeUnit.HOURS)
                    .setConstraints(constraints)
                    .addTag("REFRESH_DATA")
                    .build()

            workManager.cancelAllWorkByTag("REFRESH_DATA")
            workManager.enqueue(request)
        }
    }
}