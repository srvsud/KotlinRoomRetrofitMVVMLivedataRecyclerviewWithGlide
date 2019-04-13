package com.sudhanshu.mvvm.imageapp

import android.app.Application
import android.arch.persistence.room.Room
import com.sudhanshu.mvvm.imageapp.db.ItemsDatabase

class ImageApp : Application() {
    companion object {
        var db : ItemsDatabase? = null;
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, ItemsDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
    }
}