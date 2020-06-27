package com.example.challenge2.repo

import androidx.lifecycle.LiveData
import com.example.challenge2.FeedsModel
import com.example.challenge2.dao.FeedsDao

class FeedsRepo(private val feedsDao: FeedsDao) {
    val allFeeds: LiveData<List<FeedsModel>> = feedsDao.getAllFeeds()
    suspend fun insert(myFeed: FeedsModel) {
        feedsDao.insert(myFeed)
    }
    suspend fun insertAll(myFeeds: List<FeedsModel>) {
        feedsDao.insertAll(myFeeds)
    }
    suspend fun deleteAll() {
        feedsDao.deleteAll()
    }
    suspend fun update(myFeed: FeedsModel) {
        feedsDao.update(myFeed)
    }
    suspend fun delete(myFeed: FeedsModel) {
        feedsDao.delete(myFeed)
    }

}
