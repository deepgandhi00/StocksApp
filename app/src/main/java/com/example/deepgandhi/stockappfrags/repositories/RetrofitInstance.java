package com.example.deepgandhi.stockappfrags.repositories;

import com.example.deepgandhi.stockappfrags.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    private static Retrofit retrofit;
    private static String baseUrl = "https://services.investo2o.com";
    public static Retrofit getAutoCompleteInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .build();
        }

        return retrofit;
    }
}
