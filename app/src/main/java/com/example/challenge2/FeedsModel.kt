package com.example.challenge2

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_feeds")
data class FeedsModel(
    var caption: String,
    var location: String,
    var image: Uri?,
    @PrimaryKey var key: String?
){
    constructor() : this("","",null,null
    )
}
