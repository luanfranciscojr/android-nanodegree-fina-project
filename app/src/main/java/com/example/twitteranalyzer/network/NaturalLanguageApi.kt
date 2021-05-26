package com.example.twitteranalyzer.network

import com.example.twitteranalyzer.BuildConfig
import retrofit2.http.Body
import retrofit2.http.POST

interface NaturalLanguageApi {

    @POST("""./documents:analyzeSentiment?key=${BuildConfig.LANGUAGE_API_KEY}""")
    suspend fun getAnalyzeSentiment(@Body document: NaturalLanguage): AnalyzeSentiment

}