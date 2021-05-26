package com.example.twitteranalyzer.data.source

import com.example.twitteranalyzer.network.AnalyzeSentiment
import com.example.twitteranalyzer.network.DocumentSentiment
import com.example.twitteranalyzer.network.TwitterModel
import com.example.twitteranalyzer.repository.NaturalLanguageRepository

class FakeNaturalLanguageRepository : NaturalLanguageRepository {

    var analyzeSentiment: AnalyzeSentiment = AnalyzeSentiment(DocumentSentiment(0.0))

    override suspend fun getTweetSentiment(tweet: TwitterModel): AnalyzeSentiment {
        return analyzeSentiment
    }

}