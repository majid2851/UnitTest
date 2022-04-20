package com.example.java_unittest_mitch.ui.noteslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.java_unittest_mitch.R;
//import com.example.java_unittest_mitch.di.DaggerAppComponent;
import com.example.java_unittest_mitch.repository.NoteRepository;
import com.example.java_unittest_mitch.ui.note.NoteActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity {

    @Inject
    NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        Log.i("mag2851",noteRepository+"");

        startActivity(new Intent(this, NoteActivity.class));
    }
}