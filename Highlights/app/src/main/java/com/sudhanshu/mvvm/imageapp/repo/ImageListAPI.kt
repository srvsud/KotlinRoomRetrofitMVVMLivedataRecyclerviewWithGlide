package com.sudhanshu.mvvm.imageapp.repo

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.sudhanshu.mvvm.imageapp.ImageApp
import com.sudhanshu.mvvm.imageapp.LOG_TAG
import com.sudhanshu.mvvm.imageapp.model.ImageItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class ImageListAPI {

fun fetchImageItems(listener : ImageItemRepo.IResponseStateListener) {
   val api = RetrofitHelper.getInstance().retrofit.create<ImageListService>(ImageListService::class.java)
    api.fetchImageItems()
        .enqueue(object : Callback<List<ImageItem>> {
            override fun onFailure(call: Call<List<ImageItem>>, t: Throwable) {
                Log.e(LOG_TAG,"onFailure :: OOPS!! something went wrong..", t)
                listener.onError();
            }

            override fun onResponse(call: Call<List<ImageItem>>, response: Response<List<ImageItem>>) {
                Log.d(LOG_TAG,"onResponse :" + response.body().toString())
                when (response.code()) {
                    200 -> {
                        Thread(Runnable {
                            Log.d(LOG_TAG,response.body().toString())
                            ImageApp.db!!.imageItemDAO().deleteAllImageItems()
                            ImageApp.db!!.imageItemDAO().insertImageItems(response.body()!!)
                            val handler = Handler(Looper.getMainLooper())
                            handler.post {
                                listener.onSuccess()
                            }

                        }).start()
                    } else ->
                    listener.onError()
                }
            }

        })
}

    interface ImageListService {
        @GET("photos")
        fun fetchImageItems() : Call<List<ImageItem>>
    }
}