package com.example.java_unittest_mitch.viewmodel;

import static com.example.java_unittest_mitch.repository.NoteRepository.NOTE_TITLE_NULL;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.java_unittest_mitch.models.Note;
import com.example.java_unittest_mitch.repository.NoteRepository;
import com.example.java_unittest_mitch.ui.Resource;
import com.example.java_unittest_mitch.util.DateUtil;

import javax.inject.Inject;

public class NoteViewModel extends ViewModel
{
    private static final String TAG = "NoteViewModel";
    private final NoteRepository noteRepository;
    public enum  ViewState {VIEW,EDIT}

    private MutableLiveData<Note> liveNote=new MutableLiveData<>();
    private MutableLiveData<ViewState> viewState=new MutableLiveData<>();
    private boolean isNewNote;

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

    public LiveData<ViewState> observeViewState()
    {
        return viewState;
    }
    public void setViewState(ViewState viewState)
    {
        this.viewState.setValue(viewState);
    }
    public void setIsNewNote(boolean isNewNote)
    {
        this.isNewNote=isNewNote;
    }
    public LiveData<Resource<Integer>> saveNote()
    {
        return null;
    }
    public void updateNote(String title, String content) throws Exception
    {
        if(title == null || title.equals("")){
            throw new NullPointerException("Title can't be null");
        }
        String temp = removeWhiteSpace(content);
        if(temp.length() > 0){
            Note updatedNote = new Note(liveNote.getValue());
            updatedNote.setTitle(title);
            updatedNote.setContent(content);
            updatedNote.setTimeStamp(DateUtil.getCurrentTimeStamp());

            liveNote.setValue(updatedNote);
        }
    }

    private String removeWhiteSpace(String string){
        string = string.replace("\n", "");
        string = string.replace(" ", "");
        return string;
    }


    public void setNote(Note note) throws Exception
    {
        if (note.getTitle()==null || note.getTitle().equals(""))
        {
            throw new Exception(NOTE_TITLE_NULL);
        }
       liveNote.setValue(note);
    }
    public boolean shouldNavigationBack()
    {
        if (viewState.getValue()==ViewState.VIEW)
        {
            return true;
        }else {
            return false;
        }
    }












}
