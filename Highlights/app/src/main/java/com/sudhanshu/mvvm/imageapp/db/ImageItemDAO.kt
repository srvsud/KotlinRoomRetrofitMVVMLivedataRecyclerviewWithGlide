package com.sudhanshu.mvvm.imageapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sudhanshu.mvvm.imageapp.IMAGE_ITEM_TABLE_NAME
import com.sudhanshu.mvvm.imageapp.model.ImageItem

@Dao
interface ImageItemDAO {

    @Query("SELECT * From $IMAGE_ITEM_TABLE_NAME")
    fun getImageItemList()  : LiveData<List<ImageItem>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateImageItems(countryList: List<ImageItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageItems(countryList: List<ImageItem>)

    @Query("DELETE FROM $IMAGE_ITEM_TABLE_NAME")
    fun deleteAllImageItems()
}