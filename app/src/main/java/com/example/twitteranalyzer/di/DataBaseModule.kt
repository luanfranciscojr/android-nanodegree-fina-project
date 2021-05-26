package com.example.twitteranalyzer.di

import android.content.Context
import com.example.twitteranalyzer.database.TwitterAnalyzerDatabase
import com.example.twitteranalyzer.database.TwitterDao
import org.koin.dsl.module

val databaseModule = module {
    fun provideDaoModule(context: Context): TwitterDao {
        return TwitterAnalyzerDatabase.getInstance(context).twitterDao
    }

    single { provideDaoModule(get()) }
}
