package com.sudhanshu.mvvm.imageapp.repo;

import com.google.gson.Gson;
import com.sudhanshu.mvvm.imageapp.Const;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RetrofitHelper {
    private final Retrofit mRetrofit;

    private static RetrofitHelper mInstance;

    private RetrofitHelper(){

        mRetrofit =  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(Const.BASE_URL)
                .build();
    }

    public static RetrofitHelper getInstance() {
        if(mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if(mInstance == null) {
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;

    }
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

}
