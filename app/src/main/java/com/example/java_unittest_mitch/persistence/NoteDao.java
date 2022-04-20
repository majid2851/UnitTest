package com.example.java_unittest_mitch.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.java_unittest_mitch.models.Note;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Insert
    Single<Long> insertNote(Note note) throws Exception;

    @Query("Select * From notes")
    LiveData<List<Note>>  getNotes();

    @Delete
    Single<Integer> deleteNote(Note note) throws Exception;

    @Update
    Single<Integer> updateNote(Note note) throws Exception;








}
