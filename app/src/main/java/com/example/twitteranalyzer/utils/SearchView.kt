package com.example.twitteranalyzer.utils

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun SearchView.doOnQueryTextChanged(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                offer(newText)
                return false
            }
        }
        setOnQueryTextListener(listener)
        awaitClose { setOnQueryTextListener(null) }

    }.onStart { emit(query) }
}
