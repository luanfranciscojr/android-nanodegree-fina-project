package com.example.twitteranalyzer.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NaturalLanguage(
    @Json(name = "encodingType") var encodingType: String = "UTF8",
    @Json(name = "document") var document: NaturalLanguageDocument
)

@JsonClass(generateAdapter = true)
data class NaturalLanguageDocument(
    @Json(name = "type") var type: String = "PLAIN_TEXT",
    @Json(name = "content") var content: String
)


@JsonClass(generateAdapter = true)
data class AnalyzeSentiment(@Json(name = "documentSentiment") var documentSentiment: DocumentSentiment)

@JsonClass(generateAdapter = true)
data class DocumentSentiment(@Json(name = "score") var score: Double)