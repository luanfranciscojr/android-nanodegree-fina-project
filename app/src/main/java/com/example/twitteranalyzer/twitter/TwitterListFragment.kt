package com.example.twitteranalyzer.twitter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.twitteranalyzer.databinding.FragmentTwitterListBinding
import com.example.twitteranalyzer.utils.doOnQueryTextChanged
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

class TwitterListFragment : Fragment() {

    private lateinit var binding: FragmentTwitterListBinding
    private val viewModel by viewModel<TwitterListViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwitterListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.twitterRecycler.adapter =
            TwitterAdapter(TwitterAdapter.OnClickListener { Tweet ->
                viewModel.onTweetClicked(Tweet)
            })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer { tweet ->
            if (null != tweet) {
                this.findNavController()
                    .navigate(TwitterListFragmentDirections.actionShowAnalyzerFrament(tweet))
                viewModel.openTweetAnalyzerComplete()
            }
        })
        val searchView: SearchView = binding.searchText
        searchView.doOnQueryTextChanged()
            .debounce(300L)
            .onEach { viewModel.getTwitter(searchView.query.toString()) }
            .launchIn(lifecycleScope)
    }
}