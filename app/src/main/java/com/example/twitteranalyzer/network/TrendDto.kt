package com.example.twitteranalyzer.network

import com.squareup.moshi.Json


data class TrendsDto(@Json(name = "trends") val trends: List<TrendDto>)

data class TrendDto(
    @Json(name = "name") val name: String,
    @Json(name = "tweet_volume") val tweetVolume: Long?
)


data class TrendId(@Json(name = "woeid") val woeid: Long)