package com.example.challenge2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challenge2.FeedsModel
import com.example.challenge2.dao.FeedsDao

@Database(entities = arrayOf(FeedsModel::class), version = 1,
    exportSchema = false)

public abstract class FeedsDatabase : RoomDatabase() {

    abstract fun feedsDao(): FeedsDao
    companion object {
        @Volatile
        private var INSTANCE: FeedsDatabase? = null
        fun getDatabase(context: Context): FeedsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeedsDatabase::class.java,
                    "feeds_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}