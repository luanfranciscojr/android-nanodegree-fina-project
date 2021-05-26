package com.example.twitteranalyzer.twitteranalyze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twitteranalyzer.databinding.TwitterAnalyzeFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TwitterAnalyzeFragment : Fragment() {

    companion object {
        fun newInstance() = TwitterAnalyzeFragment()
    }

    private lateinit var binding: TwitterAnalyzeFragmentBinding
    private val viewModel by viewModel<TwitterAnalyzeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TwitterAnalyzeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tweet = TwitterAnalyzeFragmentArgs.fromBundle(requireArguments()).tweet
        binding.tweet = tweet
        viewModel.getTweetSentiment(tweet)
    }

}