package com.example.java_unittest_mitch.repository;

import static com.example.java_unittest_mitch.repository.NoteRepository.INSERT_FAILURE;
import static com.example.java_unittest_mitch.repository.NoteRepository.INSERT_SUCCESS;
import static com.example.java_unittest_mitch.repository.NoteRepository.NOTE_TITLE_NULL;
import static com.example.java_unittest_mitch.repository.NoteRepository.UPDATE_FAILURE;
import static com.example.java_unittest_mitch.repository.NoteRepository.UPDATE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.java_unittest_mitch.models.Note;
import com.example.java_unittest_mitch.persistence.NoteDao;
import com.example.java_unittest_mitch.ui.Resource;
import com.example.java_unittest_mitch.util.LiveDataTestUtil;
import com.example.java_unittest_mitch.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Not;

import java.util.List;

import io.reactivex.Single;

class NoteRepositoryTest
{
    private NoteRepository noteRepository;
     Note finalNote=new Note(TestUtil.TEST_NOTE_1);

    //@Mock
    private NoteDao noteDao;

    @BeforeEach
    public void init(){
        //MockitoAnnotations.initMocks(this);
        noteDao= Mockito.mock(NoteDao.class);//the better way for instantiate of noteDao
        noteRepository=new NoteRepository(noteDao);
    }
    /* insert note, verify the correct method is called
        confirm observer is triggered  confirm new rows inserted  */
    @Test
    public void insertNoteCheckIsCorrect() throws Exception {
        //arrange
        final Long insertedRow=1L;
        Single<Long> response=Single.just(insertedRow);
        when(noteDao.insertNote(any())).thenReturn(response);

        //act
        final Resource<Integer> returnedValue=
                noteRepository.insertNote(finalNote).blockingFirst();
        //assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);
        assertEquals(Resource.success(1,INSERT_SUCCESS),returnedValue);

        //or using Rxjava
//        noteRepository.insertNote(finalNote)
//            .test().await().assertValue(Resource.success(1,INSERT_SUCCESS));

    }

    /* insert note,i expect failure( -1 ) for error*/
    @Test
    public void insertNoteFailure() throws Exception {
        //arrange
        final Long failed=-1L;
        Single<Long> response=Single.just(failed);
        when(noteDao.insertNote(any())).thenReturn(response);

        //act
        final Resource<Integer> returnedValue=
                noteRepository.insertNote(finalNote).blockingFirst();
        //assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);
        assertEquals(Resource.error(null,INSERT_FAILURE),returnedValue);

    }

    /* insert note with null title -> throw exception  */
    @Test
    public void insertNullTitle()  {
        Exception exception=assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                finalNote.setTitle(null);
                noteRepository.insertNote(finalNote).blockingFirst();
            }
        });
        assertEquals(NOTE_TITLE_NULL,exception.getMessage());

    }
    /*
    update note
    verfiy correct method is called,
    confirm observer is trigger
    confirm number of rows updated
     */
    @Test
    void update_note_with_correct_observer_and_get_updated_rows() throws Exception {
        Note note=new Note(TestUtil.TEST_NOTE_1);
       final Integer row=1;
       Single<Integer> response=Single.just(row);
        when(noteDao.updateNote(any(Note.class))).thenReturn(response);

        Resource<Integer> returnedValue=noteRepository.updateNote(note).blockingFirst();
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);
        assertEquals(Resource.success(1,UPDATE_SUCCESS).data,row);




    }
    
    
    
    
    /*
    update note
    failure -1
     */
    @Test
    void update_note_with_failure() throws Exception {
        Note note=new Note(TestUtil.TEST_NOTE_1);
        final Integer row=-1;
        Single<Integer> response=Single.just(row);
        when(noteDao.updateNote(any(Note.class))).thenReturn(response);

        Resource<Integer> returnedValue=noteRepository.updateNote(note).blockingFirst();
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);
        assertEquals(Resource.error(null,UPDATE_FAILURE),returnedValue);


    }
    /*
    update note with null title => throws exception
     */

    @Test
    void update_note_null_title() {
        Exception exception=assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Note note=new Note(TestUtil.TEST_NOTE_1);
                note.setTitle(null);
                noteRepository.updateNote(note);
            }
        }) ;
        assertEquals(exception.getMessage(),NOTE_TITLE_NULL);


    }
}