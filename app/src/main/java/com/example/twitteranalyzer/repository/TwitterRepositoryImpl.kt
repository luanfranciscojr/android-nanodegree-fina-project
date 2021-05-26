package com.example.twitteranalyzer.repository

import android.location.Location
import androidx.lifecycle.LiveData
import com.example.twitteranalyzer.database.TwitterDao
import com.example.twitteranalyzer.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TwitterRepositoryImpl(private val api: TwitterApi, private val dao: TwitterDao) :
    TwitterRepository {


    private var tweets: LiveData<List<TwitterModel>> = dao.getTwitters()
    override fun getTweets(): LiveData<List<TwitterModel>> {
        return tweets
    }

    override suspend fun retrieveTweeters(userName: String) {
        withContext(Dispatchers.IO) {
            val tweets = api.getTwitters(userName).asTwitterModelList()
            dao.deleteAll()
            dao.insertAll(tweets);
        }
    }

    override suspend fun getTrendsByLocation(location: Location): List<TrendId> {
        return api.getTrendByLocation(location.longitude, location.latitude)
    }

    override suspend fun getTrends(id: Long): List<TrendsDto> {
        return api.getTrends(id)
    }
}