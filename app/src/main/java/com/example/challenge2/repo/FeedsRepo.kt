package com.example.challenge2.repo

import androidx.lifecycle.LiveData
import com.example.challenge2.FeedsModel
import com.example.challenge2.dao.FeedsDao

class FeedsRepo(private val feedsDao: FeedsDao) {
    // Room executes all queries on a separate thread.
// Observed LiveData will notify the observer when the data has changed.
    val allFeeds: LiveData<List<FeedsModel>> =
        feedsDao.getAllFeeds()
    suspend fun insert(myFeeds: FeedsModel) {
        feedsDao.insert(myFeeds)
    }
    suspend fun insertAll(myFeeds: List<FeedsModel>) {
        feedsDao.insertAll(myFeeds)
    }
    suspend fun deleteAll() {
        feedsDao.deleteAll()
    }
    suspend fun update(myFriend: FeedsModel) {
        feedsDao.update(myFriend)
    }
    suspend fun delete(myFriend: FeedsModel) {
        feedsDao.delete(myFriend)
    }

}
