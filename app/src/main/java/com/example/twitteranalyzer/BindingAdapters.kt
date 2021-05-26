package com.example.twitteranalyzer

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twitteranalyzer.di.ApiStatus
import com.example.twitteranalyzer.network.TrendDto
import com.example.twitteranalyzer.network.TwitterModel
import com.example.twitteranalyzer.trends.TrendsAdapter
import com.example.twitteranalyzer.twitter.TwitterAdapter

@BindingAdapter("listTweet")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<TwitterModel>?) {
    val adapter = recyclerView.adapter as TwitterAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        ApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
        ApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("tweetSentiment")
fun bindTweetSentiment(imageView: ImageView, resourceSentiment: Int) {
    imageView.setImageResource(resourceSentiment)
}

@BindingAdapter("trends")
fun bindTrendRecyclerView(recyclerView: RecyclerView, data: List<TrendDto>?) {
    val adapter = recyclerView.adapter as TrendsAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}

