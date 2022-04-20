package com.example.java_unittest_mitch.ui.note;

import static com.example.java_unittest_mitch.repository.NoteRepository.INSERT_FAILURE;
import static com.example.java_unittest_mitch.repository.NoteRepository.INSERT_SUCCESS;
import static com.example.java_unittest_mitch.repository.NoteRepository.NOTE_TITLE_NULL;
import static com.example.java_unittest_mitch.repository.NoteRepository.UPDATE_FAILURE;
import static com.example.java_unittest_mitch.repository.NoteRepository.UPDATE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.pm.LabeledIntent;

import androidx.lifecycle.LiveData;

import com.example.java_unittest_mitch.models.Note;
import com.example.java_unittest_mitch.repository.NoteRepository;
import com.example.java_unittest_mitch.ui.Resource;
import com.example.java_unittest_mitch.util.InstantExcutorExtension;
import com.example.java_unittest_mitch.util.LiveDataTestUtil;
import com.example.java_unittest_mitch.util.TestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.internal.operators.single.SingleToFlowable;

@ExtendWith(InstantExcutorExtension.class)
class NoteViewModelTest
{
    private NoteViewModel noteViewModel;


    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        noteViewModel=new NoteViewModel(noteRepository);
    }

    /*  can't observe a note that hasn't been set  */
    @Test
    public void observe_Null_Note_When_Is_Set() throws InterruptedException {
        LiveDataTestUtil<Note> liveData=new LiveDataTestUtil<>();
        Note notes=liveData.getValue(noteViewModel.observeNote());
        assertNull(notes);



    }

    /*  observe a note has been set, and onChanged trigger in Activity */
    @Test
    public void observe_Correct_Note_when_Is_Set() throws Exception {
        //arrange
        Note note=new Note(TestUtil.TEST_NOTE_1);
        noteViewModel.setNote(note);
        LiveDataTestUtil<Note> liveData=new LiveDataTestUtil<>();
        Note observeNote=liveData.getValue(noteViewModel.observeNote());
        assertEquals(observeNote,note);


        //act

        //assert

    }
    /* insert a new note, and observe returned row   */

    @Test
    public void inser_NewNote_And_Observe_ReturnedRow() throws Exception {

        //my way
//        Note note2=new Note(TestUtil.TEST_NOTE_1);
//        noteViewModel.setNote(note2);
//        LiveData<Resource<Integer>> position = noteViewModel.insertNote();
//        assertNotNull(position);
//        assertEquals(position.getValue().data,1);
        //
        Note note=new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil=new LiveDataTestUtil<>();
        final int insertedRow=1;
        Flowable<Resource<Integer>> returnedData= SingleToFlowable
                .just(Resource.success(insertedRow,INSERT_SUCCESS));
        when(noteRepository.insertNote(any(Note.class))).
                thenReturn(returnedData);
        noteViewModel.setNote(note);
        Resource<Integer> returnedValue=liveDataTestUtil.getValue(noteViewModel.insertNote());
        assertEquals(Resource.success(insertedRow,INSERT_SUCCESS),returnedValue);
    }
    /* insert,don't return a new row without observer */

    @Test
    public void insertNote_but_dont_return_newRow_without_observer() throws Exception {
        Note note=new Note(TestUtil.TEST_NOTE_1);
        noteViewModel.setNote(note);

        verify(noteRepository,never()).insertNote(any(Note.class));
    }
    /*set note with null tilte=> throw exception*/

    @Test
    public void set_Note_with_null_title_and_throw_excpetion() {
        Note note=new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);
        Exception throwType = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                noteViewModel.setNote(note);
            }
        });
       // assertEquals(throwType,NOTE_TITLE_NULL);
    }

    //update note and get row response
    @Test
    public void update_NewNote_And_Observe_ReturnedRow() throws Exception {

        //my way
//        Note note2=new Note(TestUtil.TEST_NOTE_1);
//        noteViewModel.setNote(note2);
//        LiveData<Resource<Integer>> position = noteViewModel.insertNote();
//        assertNotNull(position);
//        assertEquals(position.getValue().data,1);
        //
        Note note=new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil=new LiveDataTestUtil<>();
        final int updateRow=1;
        Flowable<Resource<Integer>> returnedData= SingleToFlowable
                .just(Resource.success(updateRow,UPDATE_SUCCESS));
        when(noteRepository.updateNote(any(Note.class))).
                thenReturn(returnedData);
        noteViewModel.setNote(note);
        Resource<Integer> returnedValue=liveDataTestUtil.getValue(noteViewModel.updateNote());
        assertEquals(Resource.success(updateRow,UPDATE_SUCCESS),returnedValue);
    }
    //update note and not getting row
    @Test
    public void updateNote_but_dont_return_newRow_without_observer() throws Exception {
        Note note=new Note(TestUtil.TEST_NOTE_1);
        noteViewModel.setNote(note);

        verify(noteRepository,never()).updateNote(any(Note.class));
    }
}