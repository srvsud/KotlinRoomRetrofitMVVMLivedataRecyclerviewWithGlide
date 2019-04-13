package com.sudhanshu.mvvm.imageapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.sudhanshu.mvvm.imageapp.EXTRA_IMG_ITEM
import com.sudhanshu.mvvm.imageapp.R
import com.sudhanshu.mvvm.imageapp.model.ImageItem
import com.sudhanshu.mvvm.imageapp.util.AppUtil
import com.sudhanshu.mvvm.imageapp.util.AppUtil.loadImage

class ImageViewActivity : AppCompatActivity() {
    private lateinit var mImageItem: ImageItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!intent.hasExtra(EXTRA_IMG_ITEM)) {
            // no need to start this activity if there is no imageItem object availabe
            finish()
            return
        }
        mImageItem = intent.getSerializableExtra(EXTRA_IMG_ITEM) as ImageItem;

        setContentView(R.layout.activity_image_view)
        findViewById<TextView>(R.id.tv_title_val).text = mImageItem.title
        findViewById<TextView>(R.id.tv_albumid_val).text = mImageItem.albumId.toString()

        // load image in an extension function for Image view and is implemented in @AppUtil.ktng Glide
        findViewById<ImageView>(R.id.img_full).loadImage(this@ImageViewActivity, mImageItem.url)
        if(!AppUtil.isNetworkConnected(this)) {
            Toast.makeText(this, "network not available", Toast.LENGTH_LONG).show()
        }
    }
}
