package com.example.twitteranalyzer.trends

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitteranalyzer.di.ApiStatus
import com.example.twitteranalyzer.network.TrendDto
import com.example.twitteranalyzer.repository.TwitterRepository
import kotlinx.coroutines.launch

class TrendsViewModel(var repository: TwitterRepository) : ViewModel() {

    var trends = MutableLiveData<List<TrendDto>>()
    val status = MutableLiveData(ApiStatus.LOADING)

    fun getTrends(location: Location) {
        viewModelScope.launch {
            try {
                status.value = ApiStatus.LOADING
                val trendId = repository.getTrendsByLocation(location)
                val result = repository.getTrends(trendId[0].woeid)
                result[0].trends.let { it -> trends.value = it };
                Log.d("teste", result[0].trends.toString())
                status.value = ApiStatus.DONE
            } catch (e: Exception) {
                trends.value = ArrayList()
                status.value = ApiStatus.ERROR
            }

        }
    }

}