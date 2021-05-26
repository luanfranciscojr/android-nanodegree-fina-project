package com.example.twitteranalyzer.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterApi {

    @GET("search/tweets.json")
    suspend fun getTwitters(@Query("q") userName: String): TwitterList

    @GET("trends/closest.json")
    suspend fun getTrendByLocation(
        @Query("lat") lat: Double,
        @Query("long") long: Double
    ): List<TrendId>

    @GET("trends/place.json")
    suspend fun getTrends(@Query("id") id: Long): List<TrendsDto>

}