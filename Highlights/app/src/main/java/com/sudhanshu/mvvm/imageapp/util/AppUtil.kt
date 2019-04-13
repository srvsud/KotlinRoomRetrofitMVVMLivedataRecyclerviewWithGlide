package com.sudhanshu.mvvm.imageapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import com.bumptech.glide.Glide


object AppUtil {
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun ImageView.loadImage(context : Context, imageUrl: String) =
        Glide.with(context)
            .load(imageUrl)
            .error(Glide.with(context).load(android.R.drawable.ic_menu_gallery))
            .into(this)
}