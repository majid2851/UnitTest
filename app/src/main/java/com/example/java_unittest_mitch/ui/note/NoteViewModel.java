package com.example.java_unittest_mitch.ui.note;

import static com.example.java_unittest_mitch.repository.NoteRepository.NOTE_TITLE_NULL;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.java_unittest_mitch.models.Note;
import com.example.java_unittest_mitch.repository.NoteRepository;
import com.example.java_unittest_mitch.ui.Resource;
import com.example.java_unittest_mitch.util.DateUtil;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class NoteViewModel  extends ViewModel
{
    private final NoteRepository noteRepository;
    private MutableLiveData<Note> liveNote=new MutableLiveData<>();
    public enum ViewState{VIEW,EDIT}
    private MutableLiveData<ViewState> viewState=new MutableLiveData<>();
    private boolean isNewNote;
    private Subscription updateSubscription,insertSubscription;

    @Inject
    public NoteViewModel(NoteRepository noteRepository){
        this.noteRepository=noteRepository;
    }
    public LiveData<Resource<Integer>> insertNote() throws Exception {
        return LiveDataReactiveStreams.fromPublisher(//converting rxjava data to live data
                noteRepository.insertNote(liveNote.getValue())
                        .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        insertSubscription=subscription;
                    }
                })
        );
    }

    public LiveData<Resource<Integer>> updateNote() throws Exception{
        return LiveDataReactiveStreams.fromPublisher(
                noteRepository.updateNote(liveNote.getValue())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                updateSubscription = subscription;
                            }
                        })
        );
    }


    public void cancelPendingTransaction() {
        if (insertSubscription!=null) cancelInsertTransaction();
        if (updateSubscription!=null) cancelUpdateTransaction();
    }
    public void cancelUpdateTransaction(){
        updateSubscription.cancel();
        updateSubscription=null;
    }
    public void cancelInsertTransaction(){
        insertSubscription.cancel();
        insertSubscription=null;
    }
    public LiveData<Note> observeNote(){
        return liveNote;
    }

    public void setNote(Note note) throws Exception{
        if (note.getTitle().equals(""))
            throw new Exception(NOTE_TITLE_NULL);
       else liveNote.setValue(note);
    }

    public void setIsNewNote(boolean isNewNote) {
        this.isNewNote=isNewNote;
    }

    public LiveData<ViewState> observeViewState() {
        return viewState;
    }

    public LiveData<Resource<Integer>> saveNote() throws Exception {
        if (!shouldAllowSave())
            throw new Exception("content is empty");
        cancelPendingTransaction();
        return new NoteInsertUpdateHelper<Integer>() {
            @Override
            public void setNoteId(int noteId) {
                isNewNote=false;
                Note currentNote=liveNote.getValue();
                currentNote.setId(noteId);
                liveNote.setValue(currentNote);
            }

            @Override
            public LiveData<Resource<Integer>> getAction() throws Exception {
                if (isNewNote)
                    return insertNote();
                else return updateNote();
            }

            @Override
            public String defineAction() {
                if (isNewNote)
                    return ACTION_INSERT;
                else return ACTION_UPDATE;
            }

            @Override
            public void onTransactionComplete() {
                updateSubscription=null;
                insertSubscription=null;
            }
        }.getAsLiveData();
    }

    public boolean shouldAllowSave(){
        return removeWhiteSpace(liveNote.getValue().getContent()).length()>0;
    }

    public void setViewState(ViewState viewState1) {
        this.viewState.setValue(viewState1);
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

    public boolean shouldNavigateBack() {
        if (viewState.getValue()==ViewState.VIEW)
            return true;
        else return false;
    }





}
