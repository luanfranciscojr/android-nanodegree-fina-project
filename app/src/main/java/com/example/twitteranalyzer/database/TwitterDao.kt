package com.example.twitteranalyzer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twitteranalyzer.network.TwitterModel

@Dao
interface TwitterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tweets: List<TwitterModel>)

    @Query("select * from twitter")
    fun getTwitters(): LiveData<List<TwitterModel>>


    @Query("delete from twitter")
    fun deleteAll()
}