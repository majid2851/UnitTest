package com.example.java_unittest_mitch.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.java_unittest_mitch.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule
{
    @Binds
    public abstract ViewModelProvider.Factory
        bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);






}
