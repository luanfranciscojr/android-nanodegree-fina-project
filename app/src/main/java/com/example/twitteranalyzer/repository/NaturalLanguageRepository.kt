package com.example.twitteranalyzer.repository

import com.example.twitteranalyzer.network.AnalyzeSentiment
import com.example.twitteranalyzer.network.TwitterModel

interface NaturalLanguageRepository {
    suspend fun getTweetSentiment(tweet: TwitterModel): AnalyzeSentiment
}