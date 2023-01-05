package com.example.java_unittest_mitch.repository;

import androidx.annotation.NonNull;

import com.example.java_unittest_mitch.persistence.NoteDao;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NoteRepository
{
    @NonNull
    private final NoteDao noteDao;

    @Inject
    public NoteRepository(@NonNull NoteDao noteDao)
    {
        this.noteDao = noteDao;
    }







}
