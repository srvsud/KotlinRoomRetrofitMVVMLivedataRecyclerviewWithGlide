package com.sudhanshu.mvvm.imageapp.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sudhanshu.mvvm.imageapp.EXTRA_IMG_ITEM
import com.sudhanshu.mvvm.imageapp.R
import com.sudhanshu.mvvm.imageapp.model.ImageItem
import com.sudhanshu.mvvm.imageapp.util.AppUtil.loadImage

class ImageItemRecyclerAdaper(ctx : Context, itemList : List<ImageItem>?) :
    RecyclerView.Adapter<ImageItemRecyclerAdaper.ItemItemViewHolder>() {

    private val mContext = ctx
    private var mItemList = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemItemViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.layout_image_item, parent, false)
        return ItemItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        if(mItemList != null) {
            return mItemList!!.size
        }
       return 0
    }

    override fun onBindViewHolder(holder: ItemItemViewHolder, position: Int) {
        val item = mItemList?.get(position)
        if (item != null) {
            holder.title.text = item.title
            holder.thumbnail.loadImage(mContext, item.thumbnailUrl)
            holder.itemView.setOnClickListener{
                val intent = Intent(mContext, ImageViewActivity::class.java)
                intent.putExtra(EXTRA_IMG_ITEM,item)
                mContext.startActivity(intent)
            }
        }
    }

    class ItemItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.tv_albumname)
        var thumbnail = itemView.findViewById<ImageView>(R.id.iv_albumthumbnail)

    }

    fun updateList(newItems : List<ImageItem>) {
       mItemList = newItems
        notifyDataSetChanged();
    }

}