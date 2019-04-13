package com.sudhanshu.mvvm.imageapp.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sudhanshu.mvvm.imageapp.ImageApp
import com.sudhanshu.mvvm.imageapp.model.ImageItem
import com.sudhanshu.mvvm.imageapp.model.RequestState

class ImageItemRepo {

    val stateLiveData =  MutableLiveData<RequestState>()

    init {
        stateLiveData.value = RequestState.NONE
    }

    fun getImageItemList(bFetchFromServer : Boolean) : LiveData<List<ImageItem>> {
        stateLiveData.value = RequestState.STARTED
        if(bFetchFromServer) {
            ImageListAPI().fetchImageItems(object : IResponseStateListener {
                override fun onSuccess() {
                    stateLiveData.value = RequestState.DONE_SUCCESS
                }

                override fun onError() {
                    stateLiveData.value = RequestState.DONE_ERROR
                }

            })
        }
        return ImageApp.db!!.imageItemDAO().getImageItemList()
    }

    fun fetchImageFromServer() {
        stateLiveData.value = RequestState.STARTED
        ImageListAPI().fetchImageItems(object : IResponseStateListener {
            override fun onSuccess() {
                stateLiveData.value = RequestState.DONE_SUCCESS
            }

            override fun onError() {
                stateLiveData.value = RequestState.DONE_ERROR
            }

        });

    }

    fun getRequestState() : LiveData<RequestState> {
        return stateLiveData
    }

    interface IResponseStateListener {
        fun onSuccess()
        fun onError()
    }
}