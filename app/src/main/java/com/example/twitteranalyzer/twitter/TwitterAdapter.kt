package com.example.twitteranalyzer.twitter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.twitteranalyzer.databinding.TwitterItemBinding
import com.example.twitteranalyzer.network.TwitterModel


class TwitterAdapter(private val clickListener: OnClickListener<TwitterModel>) :
    ListAdapter<TwitterModel, TwitterAdapter.TwitterViewHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<TwitterModel>() {

        override fun areItemsTheSame(oldItem: TwitterModel, newItem: TwitterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TwitterModel, newItem: TwitterModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TwitterViewHolder {
        return TwitterViewHolder(
            TwitterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TwitterViewHolder, position: Int) {
        val Tweet = getItem(position)
        holder.bind(clickListener, Tweet)
    }

    class TwitterViewHolder(private var binding: TwitterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: OnClickListener<TwitterModel>, twitter: TwitterModel) {
            binding.twitter = twitter
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

    }

    class OnClickListener<T>(val clickListener: (item: T) -> Unit) {
        fun onClick(item: T) = clickListener(item)
    }

}