package com.example.twitteranalyzer.repository

import com.example.twitteranalyzer.network.*

class NaturalLanguageRepositoryImpl(private val api: NaturalLanguageApi) :
    NaturalLanguageRepository {
    override suspend fun getTweetSentiment(tweet: TwitterModel): AnalyzeSentiment {
        return api.getAnalyzeSentiment(NaturalLanguage(document = NaturalLanguageDocument(content = tweet.text)))
    }
}