package com.example.deepgandhi.stockappfrags.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.util.Log;
import com.example.deepgandhi.stockappfrags.Presenters.AllStocksFrag;
import com.example.deepgandhi.stockappfrags.R;
import com.example.deepgandhi.stockappfrags.application.MyApplication;
import com.example.deepgandhi.stockappfrags.application.di.ApplicationComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    ApplicationComponent applicationComponent;
    Retrofit retrofit;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicationComponent = MyApplication.get(this).getApplicationComponent();


        retrofit = MyApplication.get(this).getAppRetrofit();

        Log.i("retrofit",retrofit.toString());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AllStocksFrag())
                .addToBackStack("main")
                .commit();
    }


    @Override
    public void onBackPressed() {
//        Log.i("backstackentry",getSupportFragmentManager().getBackStackEntryCount()+"");
//        if(getSupportFragmentManager().getBackStackEntryCount()>0){
//            getSupportFragmentManager().popBackStack();
//        }
//        else {
//            super.onBackPressed();
//        }
        super.onBackPressed();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
