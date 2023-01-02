package com.example.java_unittest_mitch.di;

import com.example.java_unittest_mitch.ui.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule
{
    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity() ;







}
