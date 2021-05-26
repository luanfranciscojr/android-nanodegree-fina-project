package com.example.twitteranalyzer.twitter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitteranalyzer.di.ApiStatus
import com.example.twitteranalyzer.network.TwitterModel
import com.example.twitteranalyzer.repository.TwitterRepository
import kotlinx.coroutines.launch

class TwitterListViewModel(private val repository: TwitterRepository) : ViewModel() {

    var tweets = repository.getTweets()

    val status = MutableLiveData(ApiStatus.LOADING)

    private val _navigateToSelectedTweet = MutableLiveData<TwitterModel?>()
    val navigateToSelectedProperty: MutableLiveData<TwitterModel?>
        get() = _navigateToSelectedTweet

    fun getTwitter(userName: String) {
        viewModelScope.launch {
            try {
                status.value = ApiStatus.LOADING
                repository.retrieveTweeters(userName)
                status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.d("teste", e.toString())
                status.value = ApiStatus.ERROR
            }

        }
    }

    fun onTweetClicked(twitterModel: TwitterModel) {
        _navigateToSelectedTweet.value = twitterModel
    }

    fun openTweetAnalyzerComplete() {
        _navigateToSelectedTweet.value = null
    }
}