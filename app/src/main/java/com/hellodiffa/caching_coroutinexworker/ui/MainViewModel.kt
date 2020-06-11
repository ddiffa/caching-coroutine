package com.hellodiffa.caching_coroutinexworker.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hellodiffa.caching_coroutinexworker.network.UserApi
import com.hellodiffa.caching_coroutinexworker.network.UsersItem
import com.hellodiffa.caching_coroutinexworker.persistance.User
import com.hellodiffa.caching_coroutinexworker.persistance.UserDao
import com.hellodiffa.caching_coroutinexworker.persistance.UserDatabase
import com.hellodiffa.caching_coroutinexworker.repository.UserRepository
import kotlinx.coroutines.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : UserRepository
    private val userDao : UserDao = UserDatabase.getInstance(application).userDao()

    private var _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> get() = _loading

    private var job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    init {
        repository = UserRepository(userDao)

        scope.launch {
            _loading.postValue(true)
            delay(1_500)
            repository.refreshUsers()
            _users.postValue(repository.getAllUser())
            _loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}