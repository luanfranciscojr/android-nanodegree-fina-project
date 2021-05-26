package com.example.twitteranalyzer

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.twitteranalyzer.network.TrendId
import com.example.twitteranalyzer.network.TrendsDto
import com.example.twitteranalyzer.network.TwitterModel
import com.example.twitteranalyzer.repository.TwitterRepository
import org.koin.dsl.module

class FakeTwitterRepository : TwitterRepository {


    override suspend fun retrieveTweeters(userName: String) {

    }

    override suspend fun getTrendsByLocation(location: Location): List<TrendId> {
        return emptyList()
    }

    override suspend fun getTrends(id: Long): List<TrendsDto> {
        return emptyList()
    }


    override fun getTweets(): LiveData<List<TwitterModel>> {
        return MutableLiveData(
            listOf(
                TwitterModel(1, "mock", "luan", "teste.url"),
                TwitterModel(2, "mock", "luan", "1")
            )
        )
    }
}

val fakeTwitterRepository = module() {
    factory<TwitterRepository> { FakeTwitterRepository() }
}
