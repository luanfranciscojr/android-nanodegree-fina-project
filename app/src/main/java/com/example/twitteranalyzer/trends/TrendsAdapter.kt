package com.example.twitteranalyzer.trends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.twitteranalyzer.databinding.TrendItemBinding
import com.example.twitteranalyzer.network.TrendDto


class TrendsAdapter() : ListAdapter<TrendDto, TrendsAdapter.TrendViewHolder>(
    DiffCallback
) {

    companion object DiffCallback : DiffUtil.ItemCallback<TrendDto>() {

        override fun areItemsTheSame(oldItem: TrendDto, newItem: TrendDto): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TrendDto, newItem: TrendDto): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TrendViewHolder {
        return TrendViewHolder(
            TrendItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trend = getItem(position)
        holder.bind(trend)
    }

    class TrendViewHolder(private var binding: TrendItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(trendDto: TrendDto) {
            binding.trend = trendDto
            binding.executePendingBindings()
        }

    }

}