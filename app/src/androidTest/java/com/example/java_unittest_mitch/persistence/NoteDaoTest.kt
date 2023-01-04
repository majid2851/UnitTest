package com.example.java_unittest_mitch.persistence

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.java_unittest_mitch.TIME1
import com.example.java_unittest_mitch.models.Note
import com.example.java_unittest_mitch.testNote
import com.example.java_unittest_mitch.util.LiveDataTestUtil
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteDaoTest:NoteDatabaseTest()
{
    @get:Rule
    val rule=InstantTaskExecutorRule()

    /*Insert,Read,Delete*/
    @Test
    fun insert_Read_delete_A_NOTE()
    {
        val note= testNote
        //---------------
        //insert
        getNoteDao().insertNote(note).blockingGet()
        //----------
        //read
        val liveUtil=LiveDataTestUtil<List<Note>>()
        var notes=liveUtil.getValue(getNoteDao().getNotes())
        Assert.assertNotNull(notes)//check inserted note

        Log.i("mag2851-noteSize-1",notes?.size.toString())
        Assert.assertEquals(notes?.get(0)?.title,note.title)
        Assert.assertEquals(notes?.get(0)?.content,note.content)
        Assert.assertEquals(notes?.get(0)?.timeStamp,note.timeStamp)

        note.id= notes?.get(0)?.id!!

        Assert.assertEquals(note,notes.get(0))
        //------------------------------------------

        Log.i("mag2851-noteSize-2",notes?.size.toString())
        //delete
        getNoteDao().deleteNote(note).blockingGet()
        notes=liveUtil.getValue(getNoteDao().getNotes())
        Log.i("mag2851-noteSize-3",notes?.size.toString())
        Assert.assertEquals(notes?.size,0)
        //-----------------------------------------------
    }
    /*-----------------------------------------------*/
    /*Insert,Read,Update,Read,Delete*/
    @Test
    fun insert_read_update_read_delete_A_Note()
    {
        val noteModel= testNote
        //Insert
        getNoteDao().insertNote(noteModel).blockingGet()
        val liveDataTestUtil=LiveDataTestUtil<List<Note>>()
        var notesData=liveDataTestUtil.getValue(getNoteDao().notes)
        Assert.assertEquals(notesData?.size,1)
        //Update----------------------------------------------
        noteModel.title="majid2851"
        noteModel.content="this is my new note"
        noteModel.timeStamp="12-1999"
        noteModel.id=notesData?.get(0)?.id!!
        getNoteDao().updateNote(noteModel).blockingGet()
        notesData=liveDataTestUtil.getValue(getNoteDao().notes)
        Assert.assertEquals(noteModel,notesData?.get(0))
        //Delete
        getNoteDao().deleteNote(noteModel).blockingGet()
        notesData=liveDataTestUtil.getValue(getNoteDao().notes)
        Assert.assertEquals(notesData?.size,0)

    }
    /*----------------------------------------------*/
    /*Insert with null Title,throw Excepti on*/
    @Test(expected = SQLiteConstraintException::class)
    fun insertWithNullTitle()
    {
        val noteModel=Note()
        noteModel.timeStamp= TIME1
        noteModel.content="this note has a null value"
        getNoteDao().insertNote(noteModel).blockingGet()
    }
    /*----------------------------------------------*/
    /*Insert,Update with null Title,throw Exception*/
    @Test(expected = SQLiteConstraintException::class)
    fun insert_update_withNUll_throwExcption()
    {
        var note= Note()
        getNoteDao().insertNote(note).blockingGet()
        val liveDataTestUtil=LiveDataTestUtil<List<Note>>()
        val notes=liveDataTestUtil.getValue(getNoteDao().notes)
        note.id= notes?.get(0)?.id!!
        note.content="content"
        note.timeStamp="05-2022"
        getNoteDao().updateNote(note).blockingGet()


    }

    /*----------------------------------------------*/

}