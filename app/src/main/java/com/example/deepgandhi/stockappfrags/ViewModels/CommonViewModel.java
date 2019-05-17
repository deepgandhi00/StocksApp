package com.example.deepgandhi.stockappfrags.ViewModels;

import com.example.deepgandhi.stockappfrags.AppExecutors;
import com.example.deepgandhi.stockappfrags.Models.GraphDetails;
import com.example.deepgandhi.stockappfrags.Models.Stocks;
import com.example.deepgandhi.stockappfrags.Models.StocksDetails;
import com.example.deepgandhi.stockappfrags.Resource;
import com.example.deepgandhi.stockappfrags.repos.ServiceRepo;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Retrofit;

public class CommonViewModel  extends ViewModel {

    ServiceRepo serviceRepo;

    @Inject
    public CommonViewModel(ServiceRepo serviceRepo){
        this.serviceRepo = serviceRepo;
    }

    public LiveData<Resource<List<Stocks>>> getStocks(){
        return serviceRepo.getStocks();
    }

    public LiveData<Resource<StocksDetails>> getStockDetails(int id){
        return serviceRepo.getDetails(id);
    }

    public LiveData<Resource<GraphDetails>> getGraphDetails(int id){
        return serviceRepo.getGraphDetails(id);
    }
}
