package com.example.twitteranalyzer.di

import com.example.twitteranalyzer.trends.TrendsViewModel
import com.example.twitteranalyzer.twitter.TwitterListViewModel
import com.example.twitteranalyzer.twitteranalyze.TwitterAnalyzeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        TwitterListViewModel(repository = get())
    }

    viewModel {
        TwitterAnalyzeViewModel(repository = get())
    }

    viewModel {
        TrendsViewModel(repository = get())
    }

}
