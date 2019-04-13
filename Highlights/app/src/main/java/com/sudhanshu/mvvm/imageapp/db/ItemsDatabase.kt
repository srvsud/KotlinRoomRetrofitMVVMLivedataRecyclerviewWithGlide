package com.sudhanshu.mvvm.imageapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sudhanshu.mvvm.imageapp.DATABASE_VERSION
import com.sudhanshu.mvvm.imageapp.model.ImageItem

@Database(entities = [(ImageItem::class)], version = DATABASE_VERSION)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun imageItemDAO() : ImageItemDAO

}