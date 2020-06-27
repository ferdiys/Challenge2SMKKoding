package com.example.challenge2.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge2.FeedsModel
import com.example.challenge2.database.FeedsDatabase
import com.example.challenge2.repo.FeedsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedsFragmentViewModel : ViewModel(){
    lateinit var repository: FeedsRepo
    lateinit var allFeeds: LiveData<List<FeedsModel>>

    fun init(context: Context) {
        val feedsDao = FeedsDatabase.getDatabase(context).feedsDao()
        repository = FeedsRepo(feedsDao)
        allFeeds = repository.allFeeds
    }
    fun delete(myFeed: FeedsModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(myFeed)
    }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(myFeeds: List<FeedsModel>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            repository.insertAll(myFeeds)
        }

}