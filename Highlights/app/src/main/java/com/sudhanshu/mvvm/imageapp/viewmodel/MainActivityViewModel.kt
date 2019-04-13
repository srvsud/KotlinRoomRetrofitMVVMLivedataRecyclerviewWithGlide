package com.sudhanshu.mvvm.imageapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.sudhanshu.mvvm.imageapp.model.ImageItem
import com.sudhanshu.mvvm.imageapp.model.RequestState
import com.sudhanshu.mvvm.imageapp.repo.ImageItemRepo

class MainActivityViewModel : ViewModel() {
    private var imageItemRepo : ImageItemRepo = ImageItemRepo()


    fun getImageItems(bRemoteFetch : Boolean) : LiveData<List<ImageItem>> {
        return imageItemRepo.getImageItemList(bRemoteFetch)
    }
    fun fetchImageItemsFromServer() {
        imageItemRepo.fetchImageFromServer()
    }

    fun getRefreshState() : LiveData<RequestState> {
        return imageItemRepo.getRequestState()
    }


}