package com.example.challenge2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.challenge2.FeedsModel

@Dao
interface FeedsDao {
    @Query("SELECT * from my_feeds")
    fun getAllFeeds(): LiveData<List<FeedsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFeed: FeedsModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(myFeeds: List<FeedsModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myFeed: FeedsModel)

    @Delete()
    suspend fun delete(myFeed: FeedsModel)

    @Query("DELETE FROM my_feeds")
    suspend fun deleteAll()
}