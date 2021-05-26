package com.example.twitteranalyzer.twitteranalyze

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.twitteranalyzer.R
import com.example.twitteranalyzer.data.source.FakeNaturalLanguageRepository
import com.example.twitteranalyzer.network.AnalyzeSentiment
import com.example.twitteranalyzer.network.DocumentSentiment
import com.example.twitteranalyzer.network.TwitterModel
import com.example.twitteranalyzer.utils.MainCoroutineRule
import com.example.twitteranalyzer.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class TwitterAnalyzeViewModelTest {

    private lateinit var viewModel: TwitterAnalyzeViewModel
    private lateinit var repository: FakeNaturalLanguageRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        repository = FakeNaturalLanguageRepository()
        viewModel = TwitterAnalyzeViewModel(repository)
    }

    @Test
    fun getTwitterAnalyze_showSadEmoji() {
        //When score of sentiment is negative
        repository.analyzeSentiment = AnalyzeSentiment(DocumentSentiment(-0.1))
        viewModel.getTweetSentiment((TwitterModel(1, "luan", "user", "teste")))

        val value = viewModel.displaySentiment.getOrAwaitValue()

        // Then Sad emoji is visivel
        MatcherAssert.assertThat(value, CoreMatchers.`is`(R.drawable.ic_android_sad))
    }

    @Test
    fun getTwitterAnalyze_showHappyEmoji() {
        //When score of sentiment is positive
        repository.analyzeSentiment = AnalyzeSentiment(DocumentSentiment(0.2))
        viewModel.getTweetSentiment((TwitterModel(1, "luan", "user", "teste")))

        val value = viewModel.displaySentiment.getOrAwaitValue()

        // Then Happy emoji is visivel
        MatcherAssert.assertThat(value, CoreMatchers.`is`(R.drawable.ic_android_happy))
    }

    @Test
    fun getTwitterAnalyze_showNormalEmoji() {
        //When score of sentiment is normal
        repository.analyzeSentiment = AnalyzeSentiment(DocumentSentiment(0.1))
        viewModel.getTweetSentiment((TwitterModel(1, "luan", "user", "teste")))

        val value = viewModel.displaySentiment.getOrAwaitValue()

        // Then Normal emoji is visivel
        MatcherAssert.assertThat(value, CoreMatchers.`is`(R.drawable.ic_android_normal))
    }


}