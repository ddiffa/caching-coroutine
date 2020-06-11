package com.hellodiffa.caching_coroutinexworker.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : List<User>)

    @Query("select * from user")
    suspend fun loadAllUser() : List<User>


}