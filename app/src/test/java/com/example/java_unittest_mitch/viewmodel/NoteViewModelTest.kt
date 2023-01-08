package com.example.java_unittest_mitch.viewmodel

import com.example.java_unittest_mitch.repository.NoteRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NoteViewModelTest
{
    private var noteVieModel:NoteViewModel ?=null

    @Mock
    private val noteRepository:NoteRepository?=null

    @BeforeEach
    fun init()
    {
        MockitoAnnotations.initMocks(this)
        noteVieModel=NoteViewModel(noteRepository)
    }

    /*can't observe a note that hasn't be set*/
    @Test
    fun canNot_observe_A_Note_thatHasNotSet()
    {


    }

    /*----------------------------------------*/

    /*Observe a note which has been set and triggerd in activity*/
    @Test
    fun observe_A_Note_thatHasSet_And_TriggerInActivity()
    {


    }


    /*-----------------------------------------*/

    /*Insert a new Note and observe a returned row*/
    @Test
    fun insert_A_NewNote_And_Observe_A_returnedRow()
    {


    }


    /*-----------------------------------------*/

    /*insert,don't return a new row without observer*/
    @Test
    fun insertNote_but_dont_return_A_newNote_withoutObserver()
    {


    }


    /*-----------------------------------------*/

    /*set note,null title,throw exeption*/
    @Test
    fun set_nullTitle_and_throws_excpetion()
    {


    }


    /*---------------------------------------------*/



}