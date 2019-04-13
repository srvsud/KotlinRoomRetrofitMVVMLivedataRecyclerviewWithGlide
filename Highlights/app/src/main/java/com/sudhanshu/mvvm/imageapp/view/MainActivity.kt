package com.sudhanshu.mvvm.imageapp.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.sudhanshu.mvvm.imageapp.LOG_TAG
import com.sudhanshu.mvvm.imageapp.R
import com.sudhanshu.mvvm.imageapp.model.ImageItem
import com.sudhanshu.mvvm.imageapp.model.RequestState
import com.sudhanshu.mvvm.imageapp.util.AppUtil.isNetworkConnected
import com.sudhanshu.mvvm.imageapp.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var  mRecyclerAdapter : ImageItemRecyclerAdaper
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var mViewModel : MainActivityViewModel
    private lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recycler)
        swipeContainer = findViewById(R.id.swipeContainer)
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        swipeContainer.setOnRefreshListener(onSwipeRefreshListener)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerAdapter = ImageItemRecyclerAdaper(this, null)
        mRecyclerView.adapter = mRecyclerAdapter
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        observeImageItemList()
        observeRefreshState()
    }

    private var onSwipeRefreshListener =
        SwipeRefreshLayout.OnRefreshListener {
            Log.d(LOG_TAG, "onRefresh")
            if(isNetworkConnected(this@MainActivity)) {
                mViewModel.fetchImageItemsFromServer()
            } else {
                Toast.makeText(this, "network not available", Toast.LENGTH_LONG).show()
                swipeContainer.isRefreshing = false
            }
    }

    private fun observeImageItemList() {
        val bConnected = isNetworkConnected(this)
        mViewModel.getImageItems(bConnected)
            .observe( this, Observer<List<ImageItem>>(function = { imageItemList ->
                Log.e(MainActivity::class.java.simpleName, imageItemList.toString())
                if (imageItemList != null) {
                    refreshList(imageItemList)
                }
                swipeContainer.isRefreshing = false


            }))
    }

    private fun observeRefreshState() {
        mViewModel.getRefreshState().observe(this@MainActivity, Observer {
            if(it != RequestState.STARTED) {
                swipeContainer.isRefreshing = false
            }
        })
    }

    private fun refreshList(items : List<ImageItem>) {
        mRecyclerAdapter.updateList(items)
    }

}
