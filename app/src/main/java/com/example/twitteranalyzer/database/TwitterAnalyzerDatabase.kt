package com.example.twitteranalyzer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.twitteranalyzer.network.TwitterModel

@Database(entities = [TwitterModel::class], version = 1, exportSchema = false)
abstract class TwitterAnalyzerDatabase : RoomDatabase() {

    abstract val twitterDao: TwitterDao

    companion object {

        @Volatile
        private var INSTANCE: TwitterAnalyzerDatabase? = null

        fun getInstance(context: Context): TwitterAnalyzerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TwitterAnalyzerDatabase::class.java,
                        "twitter_analyzer_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
