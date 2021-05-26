package com.example.twitteranalyzer

import android.app.Application
import com.example.twitteranalyzer.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TwitterAnalyzerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TwitterAnalyzerApplication)
            modules(
                listOf(
                    apiModule,
                    databaseModule,
                    networkModule,
                    viewModelModule,
                    repositoryModule,
                )
            )
        }
    }
}