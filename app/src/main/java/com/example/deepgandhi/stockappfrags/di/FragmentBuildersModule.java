package com.example.deepgandhi.stockappfrags.di;

import com.example.deepgandhi.stockappfrags.Presenters.AllStocksFrag;
import com.example.deepgandhi.stockappfrags.Presenters.FavRecycler;
import com.example.deepgandhi.stockappfrags.Presenters.FavTables;
import com.example.deepgandhi.stockappfrags.Presenters.GraphFrag;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract AllStocksFrag allStocksFrag();

    @ContributesAndroidInjector
    abstract FavTables favTables();

    @ContributesAndroidInjector
    abstract GraphFrag graphFrag();

    @ContributesAndroidInjector
    abstract FavRecycler favRecycler();
}
