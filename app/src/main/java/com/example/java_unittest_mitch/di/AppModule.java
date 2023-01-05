package com.example.java_unittest_mitch.di;

import static com.example.java_unittest_mitch.persistence.NoteDatabase.DATABASE_NAME;

import android.app.Application;

import androidx.room.Room;

import com.example.java_unittest_mitch.persistence.NoteDao;
import com.example.java_unittest_mitch.persistence.NoteDatabase;
import com.example.java_unittest_mitch.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule
{
    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(application,NoteDatabase.class,DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDatabase)
    {
        return noteDatabase.getNoteDao();
    }


    @Singleton
    @Provides
    static NoteRepository noteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }


}
