package com.example.java_unittest_mitch.viewmodel;

import static com.example.java_unittest_mitch.repository.NoteRepository.NOTE_TITLE_NULL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.java_unittest_mitch.models.Note;
import com.example.java_unittest_mitch.repository.NoteRepository;
import com.example.java_unittest_mitch.ui.Resource;

import javax.inject.Inject;

public class NoteViewModel extends ViewModel
{
    private static final String TAG = "NoteViewModel";
    private final NoteRepository noteRepository;

    private MutableLiveData<Note> liveNote=new MutableLiveData<>();

    @Inject
    public NoteViewModel(NoteRepository noteRepository)
    {
        this.noteRepository=noteRepository;
    }
    public LiveData<Resource<Integer>> insertNote() throws Exception
    {
        return LiveDataReactiveStreams.fromPublisher(noteRepository.insertNote(liveNote.getValue()));
    }

//    public LiveData<Resource<Integer>> updateNote(Note note) throws Exception
//    {
//
//    }
//    public void saveNote()
//    {
//
//    }

    public LiveData<Note> observeNote()
    {
        return liveNote;
    }


    public void setNote(Note note) throws Exception
    {
        if (note.getTitle()==null || note.getTitle().equals(""))
        {
            throw new Exception(NOTE_TITLE_NULL);
        }
       liveNote.setValue(note);
    }













}
