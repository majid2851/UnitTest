package com.example.java_unittest_mitch.ui.notes_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.java_unittest_mitch.R;
import com.example.java_unittest_mitch.ui.note.NoteActivity;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        startActivity(new Intent(NotesListActivity.this, NoteActivity.class));


    }
}