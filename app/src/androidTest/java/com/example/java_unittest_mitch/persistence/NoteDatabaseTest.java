package com.example.java_unittest_mitch.persistence;


import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.java_unittest_mitch.models.Note;
import com.example.java_unittest_mitch.util.TestUtil;

import org.junit.After;
import org.junit.Before;

public abstract class NoteDatabaseTest {
    //System under test
    public NoteDatabase noteDatabase;


    public NoteDao getNoteDao(){
        return noteDatabase.noteDao();
    }

    @Before
    public void setup(){
        noteDatabase= Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
               NoteDatabase.class
        ).build();//create a temporary db for testing
    }




    @After
    public void finish(){
        noteDatabase.close();
    }



}