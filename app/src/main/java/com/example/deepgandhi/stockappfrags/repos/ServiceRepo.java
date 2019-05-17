package com.example.deepgandhi.stockappfrags.repos;

import com.example.deepgandhi.stockappfrags.AbsentLiveData;
import com.example.deepgandhi.stockappfrags.ApiResponse;
import com.example.deepgandhi.stockappfrags.AppExecutors;
import com.example.deepgandhi.stockappfrags.Models.GraphDetails;
import com.example.deepgandhi.stockappfrags.Models.Stocks;
import com.example.deepgandhi.stockappfrags.Models.StocksDetails;
import com.example.deepgandhi.stockappfrags.NetworkBoundResource;
import com.example.deepgandhi.stockappfrags.Resource;
import com.example.deepgandhi.stockappfrags.repositories.RetrofitInstance;
import com.example.deepgandhi.stockappfrags.repositories.RetrofitServices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class ServiceRepo {

//    Retrofit retrofit;

    RetrofitServices services;

    AppExecutors appExecutors;

    List<Stocks> list = new ArrayList<>();

    StocksDetails stocksDetails;

    GraphDetails graphDetails;

    @Inject
    public ServiceRepo(AppExecutors appExecutors,RetrofitServices services){
        this.appExecutors = appExecutors;
//        this.retrofit = retrofit;
//        services = retrofit.create(RetrofitServices.class);
        this.services = services;
    }


    public LiveData<Resource<List<Stocks>>> getStocks(){

        return new NetworkBoundResource<List<Stocks>, List<Stocks>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<Stocks> item) {
                list=item;
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Stocks> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Stocks>> loadFromDb() {
                if (list == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<List<Stocks>>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(list);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Stocks>>> createCall() {
                return services.getStocks("aple","STK","false");
            }
        }.asLiveData();
    }


    public LiveData<Resource<StocksDetails>> getDetails(int id){
        return new NetworkBoundResource<StocksDetails, StocksDetails>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull StocksDetails item) {
                stocksDetails = item;
            }

            @Override
            protected boolean shouldFetch(@Nullable StocksDetails data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<StocksDetails> loadFromDb() {
                if (stocksDetails == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<StocksDetails>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(stocksDetails);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<StocksDetails>> createCall() {
                return services.getDetails("false","STK",id);
            }
        }.asLiveData();
    }


    public LiveData<Resource<GraphDetails>> getGraphDetails(int id){
        return new NetworkBoundResource<GraphDetails, GraphDetails>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull GraphDetails item) {
                graphDetails = item;
            }

            @Override
            protected boolean shouldFetch(@Nullable GraphDetails data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<GraphDetails> loadFromDb() {
                if (graphDetails == null) {
                    return AbsentLiveData.create();
                } else {
                    return new LiveData<GraphDetails>() {
                        @Override
                        protected void onActive() {
                            super.onActive();
                            setValue(graphDetails);
                        }
                    };
                }
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GraphDetails>> createCall() {
                return services.getGraphs(id);
            }
        }.asLiveData();
    }
}
