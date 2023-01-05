package com.example.java_unittest_mitch.repository

import com.example.java_unittest_mitch.persistence.NoteDao
import org.junit.jupiter.api.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NoteRepositoryTest
{
    var noteRepository:NoteRepository ?=null

    private var noteDao:NoteDao ?=null

    @BeforeEach
    fun initEach()
    {
        noteDao=Mockito.mock(NoteDao::class.java)
        noteRepository= NoteRepository(noteDao!!)
    }

    /*insert Note,
    verify the correct method is called,
    confirm observerd is triggerd,
    * confirm new rows inserted,*/
    @Test
    fun insertNote_ReturnRow()
    {



    }
    /*----------------------------------------------------------*/
    /*Insert Note,Failure(return -1)*/
    @Test
    fun insertNote_returnFailure()
    {


    }
    /*------------------------------------------------------------*/
    /*insert note,null title,confirm throws exception*/
    @Test
    fun insertNote_withNullTtitle_throwsException()
    {


    }

    /*---------------------------------------------------------------*/



}