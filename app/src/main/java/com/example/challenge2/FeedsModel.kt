package com.example.challenge2

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_feeds")
data class FeedsModel(
    var title: String,
    var caption: String,
    var date: String,
    @PrimaryKey var key: String
){
    constructor() : this("","","",""
    )
}
