package com.example.java_unittest_mitch.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.database.sqlite.SQLiteConstraintException;
import android.text.TextUtils;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.example.java_unittest_mitch.models.Note;
import com.example.java_unittest_mitch.util.LiveDataTestUtil;
import com.example.java_unittest_mitch.util.TestUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.Not;

import java.util.List;

public class NoteDaoTest extends NoteDatabaseTest
{

    @Rule
    public InstantTaskExecutorRule rule=new InstantTaskExecutorRule();
    //this doesn't work in junit5
    /*
        Insert,Read,Delete
     */
    @Test
    public void testInsertReadAndDelete() throws Exception {
        //insert
        Note note1=new Note(TestUtil.TEST_NOTE_1);

        getNoteDao().insertNote(note1).blockingGet();

        //read
        LiveDataTestUtil<List<Note>> liveData=new LiveDataTestUtil<>();
        List<Note> insertedNotes=liveData.getValue(getNoteDao().getNotes());
        assertNotNull(insertedNotes);
        note1.setId(insertedNotes.get(0).getId());
        assertEquals(note1.getTitle(),insertedNotes.get(0).getTitle());
        assertEquals(note1.getContent(),insertedNotes.get(0).getContent());
        assertEquals(note1.getTimeStamp(),insertedNotes.get(0).getTimeStamp());
        assertEquals(note1,insertedNotes.get(0));
        //delete
        getNoteDao().deleteNote(note1).blockingGet();
        insertedNotes=liveData.getValue(getNoteDao().getNotes());
        assertEquals(0,insertedNotes.size());

    }
    /*
        Insert,Read,Update,Read,Delete
     */
    @Test
    public void testInsertReadUpdateReadDelete() throws Exception {
        //insert
        Note note=new Note(TestUtil.TEST_NOTE_2);
        getNoteDao().insertNote(note).blockingGet();
        //read
        LiveDataTestUtil<List<Note>> liveDataTestUtil=new LiveDataTestUtil<>();
        List<Note> notes=liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertNotNull(notes);
        //update
        note.setTitle("title1");
        note.setContent("content1");
        note.setTimeStamp("today");
        note.setId(notes.get(0).getId());

        getNoteDao().updateNote(note).blockingGet();
        //read
        notes=liveDataTestUtil.getValue(getNoteDao().getNotes());
        Log.i("mag2851",notes.get(0).getTitle()+"\n"+note.getTitle());

        assertEquals(note.getTitle(),notes.get(0).getTitle());
        assertEquals(note.getContent(),notes.get(0).getContent());
        assertEquals(note.getTimeStamp(),notes.get(0).getTimeStamp());

        //delete
        getNoteDao().deleteNote(note).blockingGet();
        notes=liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(0,notes.size());

    }


    /*
        Insert note with null title => throws excpetion
     */

    @Test(expected = SQLiteConstraintException.class)
    public void insertNotWithNullTitle() throws Exception {
        Note note=new Note(1,null,"content","time");
        getNoteDao().insertNote(note).blockingGet();



    }

    /*
        Insert,Read,Update with null title => throws exception
     */
    @Test(expected = SQLiteConstraintException.class)
    public void insertReadAndUpdateWithNull() throws Exception {
        //insert
        Note note=new Note(TestUtil.TEST_NOTE_1);
        getNoteDao().insertNote(note).blockingGet();
        LiveDataTestUtil<List<Note>> liveDataTestUtil=new LiveDataTestUtil<>();
        List<Note> notes=liveDataTestUtil.getValue(getNoteDao().getNotes());

        //read
        assertNotNull(notes);
        //update title with null
        note.setTitle(null);
        note.setId(notes.get(0).getId());
        getNoteDao().updateNote(note).blockingGet();


    }

}