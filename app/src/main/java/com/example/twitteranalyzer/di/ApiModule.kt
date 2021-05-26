package com.example.twitteranalyzer.di

import com.example.twitteranalyzer.BuildConfig
import com.example.twitteranalyzer.network.NaturalLanguageApi
import com.example.twitteranalyzer.network.TwitterApi
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideTwitterApi(retrofit: Retrofit): TwitterApi {
        return retrofit.create(TwitterApi::class.java)
    }

    fun provideNaturalLanguageApi(retrofit: Retrofit): NaturalLanguageApi {
        return retrofit.create(NaturalLanguageApi::class.java)
    }

    single {
        provideTwitterApi(get(parameters = {
            parametersOf(
                "https://api.twitter.com/1.1/", BuildConfig.TWITTER_API_KEY
            )
        }))
    }

    single {
        provideNaturalLanguageApi(get(parameters = {
            parametersOf(
                "https://language.googleapis.com/v1/", ""
            )
        }))
    }

}