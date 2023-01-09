package com.example.java_unittest_mitch.di;

import com.example.java_unittest_mitch.ui.note.NoteActivity;
import com.example.java_unittest_mitch.ui.notes_list.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule
{
    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity() ;


    @ContributesAndroidInjector
    abstract NoteActivity noteActivity() ;





}
