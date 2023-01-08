package com.example.java_unittest_mitch.repository

import com.example.java_unittest_mitch.TIME1
import com.example.java_unittest_mitch.models.Note
import com.example.java_unittest_mitch.persistence.NoteDao
import com.example.java_unittest_mitch.repository.NoteRepository.*
import com.example.java_unittest_mitch.testNote
import com.example.java_unittest_mitch.ui.Resource
import io.reactivex.Single
import org.junit.jupiter.api.*
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import kotlin.Exception

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
        val insertedRow=1L
        val returnedData=Single.just(insertedRow)
        Mockito.`when`(noteDao?.insertNote(any(Note::class.java))).thenReturn(returnedData)

        val returnedValue=noteRepository?.insertNote(testNote)?.blockingFirst()

        verify(noteDao)?.insertNote(any(Note::class.java))
        verifyNoMoreInteractions(noteDao)

        System.out.println("Returned Value:"+returnedValue?.data.toString())
        Assertions.assertEquals(Resource.success(1,INSERT_SUCCESS),returnedValue)
        /*------------Another Way------------------*/
//        noteRepository?.insertNote(testNote)?.test()?.await()?.
//            assertValue(Resource.success(1, INSERT_SUCCESS),)

    }
    /*----------------------------------------------------------*/
    /*Insert Note,Failure(return -1)*/
    @Test
    fun insertNote_returnFailure()
    {
        val insertedRow=-1L
        val returnedData=Single.just(insertedRow)
        Mockito.`when`(noteDao?.insertNote(any(Note::class.java))).thenReturn(returnedData)

        val returnedValue=noteRepository?.insertNote(testNote)?.blockingFirst()
       verify(noteDao)?.insertNote(any(Note::class.java))
       verifyNoMoreInteractions(noteDao)

        System.out.println("Returned Value:"+returnedValue?.data.toString())
        Assertions.assertEquals(Resource.error(null, INSERT_FAILURE),returnedValue)

    }
    /*------------------------------------------------------------*/
    /*insert note,null title,confirm throws exception*/
    @Test
    fun insertNote_withNullTitle_throwsException()
    {
       val exception= Assertions.assertThrows(Exception::class.java,{
            val note= Note()
            note.timeStamp= TIME1
            note.content="content"

            noteRepository?.insertNote(note)
        })
        Assertions.assertEquals(NOTE_TITLE_NULL,exception.message)

    }

    /*---------------------------------------------------------------*/
    /*updateNote,verify correctMethod is called,confirm observer is trigger,
    * confirm number of rows updated*/
    @Test
    fun insertNote_updateWithAnotherValues_observeUpdatedNote()
    {
        val updatedRow=1
        val returnedData=Single.just(updatedRow)
        Mockito.`when`(noteDao?.updateNote(any(Note::class.java))).thenReturn(returnedData)

        val returnedValue=noteRepository?.updateNote(testNote)?.blockingFirst()

        verify(noteDao)?.updateNote(any(Note::class.java))
        verifyNoMoreInteractions(noteDao)

        System.out.println("Returned Value:"+returnedValue?.data.toString())
        Assertions.assertEquals(Resource.success(1, UPDATE_SUCCESS),returnedValue)



    }
    /*------------------------------------------*/
    /*Update note,failure (-1)*/
    @Test
    fun updateNote_withFailure()
    {
        val insertedRow=-1L
        val returnedData=Single.just(insertedRow)
        Mockito.`when`(noteDao?.insertNote(any(Note::class.java))).thenReturn(returnedData)

        val returnedValue=noteRepository?.insertNote(testNote)?.blockingFirst()
        verify(noteDao)?.insertNote(any(Note::class.java))
        verifyNoMoreInteractions(noteDao)

        System.out.println("Returned Value:"+returnedValue?.data.toString())
        Assertions.assertEquals(Resource.error(null, INSERT_FAILURE),returnedValue)
    }

    /*-----------------------------------------*/

    /*Update Note,null Title,throw exception*/
    @Test
    fun updateNote_withNull_title()
    {
        val exception= Assertions.assertThrows(Exception::class.java,{
            val note= Note()
            note.timeStamp= TIME1
            note.content="content"

            noteRepository?.updateNote(note)
        })
        Assertions.assertEquals(NOTE_TITLE_NULL,exception.message)

    }



    /*-------------------------------------------*/
}