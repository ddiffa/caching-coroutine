package com.hellodiffa.caching_coroutinexworker.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hellodiffa.caching_coroutinexworker.persistance.UserDatabase
import com.hellodiffa.caching_coroutinexworker.repository.UserRepository
import retrofit2.HttpException

class UserWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val userDao = UserDatabase.getInstance(applicationContext).userDao()
        val repository = UserRepository(userDao)

        return try {
            repository.refreshUsers()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }

    }

}