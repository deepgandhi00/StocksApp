package com.example.deepgandhi.stockappfrags.application.di;


import android.app.Application;

import com.example.deepgandhi.stockappfrags.application.MyApplication;
import com.example.deepgandhi.stockappfrags.di.FragmentBuildersModule;
import com.example.deepgandhi.stockappfrags.di.MainActivityModule;
import com.example.deepgandhi.stockappfrags.di.UrlModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        UrlModule.class,
        MainActivityModule.class,
        FragmentBuildersModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }


    void inject(MyApplication application);
}
