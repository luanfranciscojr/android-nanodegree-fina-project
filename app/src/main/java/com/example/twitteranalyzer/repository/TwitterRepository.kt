package com.example.twitteranalyzer.repository

import android.location.Location
import androidx.lifecycle.LiveData
import com.example.twitteranalyzer.network.TrendId
import com.example.twitteranalyzer.network.TrendsDto
import com.example.twitteranalyzer.network.TwitterModel

interface TwitterRepository {
    suspend fun retrieveTweeters(userName: String)

    suspend fun getTrendsByLocation(location: Location): List<TrendId>

    suspend fun getTrends(id: Long): List<TrendsDto>

    fun getTweets(): LiveData<List<TwitterModel>>

}