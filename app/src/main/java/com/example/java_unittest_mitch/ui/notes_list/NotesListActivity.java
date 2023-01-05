package com.example.java_unittest_mitch.ui.notes_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.java_unittest_mitch.R;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);



    }
}