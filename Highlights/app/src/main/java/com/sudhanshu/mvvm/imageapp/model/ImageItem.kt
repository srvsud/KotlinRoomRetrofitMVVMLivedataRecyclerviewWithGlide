package com.sudhanshu.mvvm.imageapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.sudhanshu.mvvm.imageapp.IMAGE_ITEM_TABLE_NAME
import java.io.Serializable

@Entity(tableName = IMAGE_ITEM_TABLE_NAME)
data class ImageItem (

    @PrimaryKey
    var id : Int,
    var albumId : Int,
    var title : String,
    var url : String,
    var thumbnailUrl : String
) : Serializable