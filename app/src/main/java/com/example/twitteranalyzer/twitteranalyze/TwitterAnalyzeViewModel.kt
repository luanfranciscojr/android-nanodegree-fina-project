package com.example.twitteranalyzer.twitteranalyze

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitteranalyzer.R
import com.example.twitteranalyzer.di.ApiStatus
import com.example.twitteranalyzer.network.AnalyzeSentiment
import com.example.twitteranalyzer.network.TwitterModel
import com.example.twitteranalyzer.repository.NaturalLanguageRepository
import kotlinx.coroutines.launch

class TwitterAnalyzeViewModel(var repository: NaturalLanguageRepository) : ViewModel() {

    val status = MutableLiveData(ApiStatus.LOADING)
    var tweetSentiment = MutableLiveData<AnalyzeSentiment>()
    var displaySentiment: LiveData<Int> = map(tweetSentiment) {
        when (it.documentSentiment.score) {
            in 0.2..1.0 -> R.drawable.ic_android_happy
            in -1.0..0.0 -> R.drawable.ic_android_sad
            else -> R.drawable.ic_android_normal
        }

    }

    fun getTweetSentiment(tweet: TwitterModel) {
        viewModelScope.launch {
            try {
                tweetSentiment.value = repository.getTweetSentiment(tweet)
                status.value = ApiStatus.DONE
            } catch (e: Exception) {
                status.value = ApiStatus.ERROR
            }
        }
    }
}