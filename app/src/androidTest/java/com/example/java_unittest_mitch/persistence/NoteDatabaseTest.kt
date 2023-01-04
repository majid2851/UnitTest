package com.example.java_unittest_mitch.persistence

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.java_unittest_mitch.testNote
import org.junit.After
import org.junit.Before

abstract class NoteDatabaseTest
{
    private val note= testNote
    /*Test if database name is notes_db*/
    var noteDatabase:NoteDatabase ?=null
    fun getNoteDao():NoteDao
    {
        return noteDatabase!!.getNoteDao()
    }


    @Before
    fun init()
    {
        noteDatabase= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java).build()
    }
    @After
    fun finishDatabase()
    {
        noteDatabase?.close()
    }


}