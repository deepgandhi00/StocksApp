package com.example.deepgandhi.stockappfrags.application.di;


import com.example.deepgandhi.stockappfrags.AppViewModelFactory;
import com.example.deepgandhi.stockappfrags.ViewModelSubComponent;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelSubComponent.class)
public class ApplicationModule {

    private static String baseUrl = "https://services.investo2o.com";

//    @ApplicationScope
//    @Provides
//    RetrofitServices getServices(Retrofit retrofit){
//        return retrofit.create(RetrofitServices.class);
//    }

//    @ApplicationScope
//    @Provides
//    Retrofit getRetrofit(LiveDataCallAdapterFactory liveDataCallAdapterFactory, GsonConverterFactory gsonConverterFactory){
//        return new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addCallAdapterFactory(liveDataCallAdapterFactory)
//                .addConverterFactory(gsonConverterFactory)
//                .build();
//    }
//
//    @ApplicationScope
//    @Provides
//    LiveDataCallAdapterFactory getFactory(){
//        return new LiveDataCallAdapterFactory();
//    }
//
//    @ApplicationScope
//    @Provides
//    GsonConverterFactory getConverter(Gson gson){
//        return GsonConverterFactory.create(gson);
//    }
//
//    @ApplicationScope
//    @Provides
//    Gson getGson(){
//        return new Gson();
//    }

    @Provides
    ViewModelProvider.Factory getProviders(ViewModelSubComponent.Builder viewModelSubComponents){
        return new AppViewModelFactory(viewModelSubComponents.build());
    }
}
