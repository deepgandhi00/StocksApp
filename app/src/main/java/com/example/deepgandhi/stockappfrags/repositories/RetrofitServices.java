package com.example.deepgandhi.stockappfrags.repositories;

import com.example.deepgandhi.stockappfrags.ApiResponse;
import com.example.deepgandhi.stockappfrags.Models.GraphDetails;
import com.example.deepgandhi.stockappfrags.Models.Stocks;
import com.example.deepgandhi.stockappfrags.Models.StocksDetails;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitServices {

    @GET("/assetmanager-ws/api/v1/assets/getassets")
    @Headers({
            "Access-Token: ZjAwNGNkOWQtODQzMC00Mzk5LTk4NTgtYTViMDQ1ZjcwZDVkJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
            "User-ID: 4021",
            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<List<Stocks>>> getStocks(@Query("query") String query,
                                                 @Query("Type") String type,
                                                 @Query("isCustom") String isCutsom);



    @GET("/assetmanager-ws/api/v3/getassetdetails")
    @Headers({
            "Access-Token: ZjAwNGNkOWQtODQzMC00Mzk5LTk4NTgtYTViMDQ1ZjcwZDVkJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
            "User-ID: 4021",
            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<StocksDetails>> getDetails(@Query("isCustom") String isCustom,
                                   @Query("type") String type,
                                   @Query("asset") int asset);



    @GET("/assetmanager-ws/api/v3/favourites/assets/trends")
    @Headers({
            "Access-Token: ZjAwNGNkOWQtODQzMC00Mzk5LTk4NTgtYTViMDQ1ZjcwZDVkJVVTRVIlNDAyMQ==",
            "Agent: Mozilla/5.0 (Linux; Android 7.1.1; Android SDK built for x86 Build/NYC; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36",
            "User-ID: 4021",
            "User-IP: 0.0.0.0"
    })
    LiveData<ApiResponse<GraphDetails>> getGraphs(@Query("assetId") int id);
}
