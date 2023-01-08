package com.example.java_unittest_mitch.viewmodel

import com.example.java_unittest_mitch.TIME1
import com.example.java_unittest_mitch.models.Note
import com.example.java_unittest_mitch.repository.NoteRepository
import com.example.java_unittest_mitch.repository.NoteRepository.INSERT_FAILURE
import com.example.java_unittest_mitch.repository.NoteRepository.INSERT_SUCCESS
import com.example.java_unittest_mitch.testNote
import com.example.java_unittest_mitch.ui.Resource
import com.example.java_unittest_mitch.util.InstantExcuterExtension
import com.example.java_unittest_mitch.util.LiveDataTestUtil
import io.reactivex.internal.operators.single.SingleToFlowable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Resources
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExcuterExtension::class)
class NoteViewModelTest
{
    private var noteViewModel: NoteViewModel? = null

    @Mock
    private val noteRepository: NoteRepository? = null

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        noteViewModel = NoteViewModel(noteRepository)
    }

    /*can't observe a note that hasn't be set*/
    @Test
    fun canNot_observe_A_Note_thatHasNotSet()
    {
        val liveDataTestUtil=LiveDataTestUtil<Note>()
        val data=liveDataTestUtil.getValue(noteViewModel!!.observeNote())
        assertNull(data)

    }

    /*----------------------------------------*/

    /*Observe a note which has been set and triggerd in activity*/
    @Test
    fun observe_A_Note_thatHasSet_And_TriggerInActivity()
    {
        val liveDataTestUtil=LiveDataTestUtil<Note>()
        noteViewModel?.setNote(testNote)
        val data=liveDataTestUtil.getValue(noteViewModel!!.observeNote())
        assertEquals(testNote,data)
    }


    /*-----------------------------------------*/

    /*Insert a new Note and observe a returned row*/
    @Test
    fun insert_A_NewNote_And_Observe_A_returnedRow()
    {
        /*My Way*/
//        val liveDataTestUtil=LiveDataTestUtil<Note>()
//        noteViewModel?.setNote(testNote)
//        noteViewModel?.insertNote()
//        val observedNote=liveDataTestUtil.getValue(noteViewModel!!.observeNote())
//        testNote.id= observedNote?.id ?: 0
//        Assertions.assertEquals(testNote,observedNote)
        /**/

        val liveDataTestUtil=LiveDataTestUtil<Resource<Int>>()
        val insertedRow=1
        val returnedData=SingleToFlowable.just(Resource.success(insertedRow,INSERT_SUCCESS))
        Mockito.`when`(noteRepository?.insertNote(any(Note::class.java))).thenReturn(returnedData)

        noteViewModel?.setNote(testNote)
        val returnedValue=liveDataTestUtil.getValue(noteViewModel!!.insertNote())

        assertEquals(returnedValue,Resource.success(insertedRow, INSERT_SUCCESS))

    }


    /*-----------------------------------------*/

    /*insert,don't return a new row without observer*/
    @Test
    fun insertNote_but_dont_return_A_newNote_withoutObserver()
    {
        /*My Way*/
//        val liveDataTestUtil=LiveDataTestUtil<Resource<Int>>()
//        val insertedRow=1
//        val returnedData=SingleToFlowable.just(Resource.success(insertedRow,INSERT_FAILURE))
//        Mockito.`when`(noteRepository?.insertNote(any(Note::class.java))).thenReturn(returnedData)
//
//        val returnedValue=liveDataTestUtil.getValue(noteViewModel!!.insertNote())
//        Assertions.assertEquals(returnedValue,Resource.error(insertedRow, INSERT_FAILURE))
        /*--*/
        noteViewModel?.setNote(testNote)
        verify(noteRepository, never())?.insertNote(any(Note::class.java))



    }


    /*-----------------------------------------*/

    /*set note,null title,throw exeption*/
    @Test
    fun set_nullTitle_and_throws_excpetion()
    {
        val exception=  assertThrows(Exception::class.java,{
            val note= Note()
            note.timeStamp= TIME1
            note.content="content"
            noteViewModel?.setNote(note)

            noteViewModel?.insertNote()
        })
     assertEquals(NoteRepository.NOTE_TITLE_NULL,exception.message)

    }
    /*---------------------------------------------*/



}