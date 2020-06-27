package com.example.challenge2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge2.FeedsModel
import com.example.challenge2.database.FeedsDatabase
import com.example.challenge2.repo.FeedsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateFeedsViewModel : ViewModel() {
    lateinit var repository: FeedsRepo
    fun init(context: Context) {
        val myFeedsDao = FeedsDatabase.getDatabase(context).feedsDao()
        repository = FeedsRepo(myFeedsDao)
    }
    fun updateData(myFriend: FeedsModel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(myFriend)
        }
}