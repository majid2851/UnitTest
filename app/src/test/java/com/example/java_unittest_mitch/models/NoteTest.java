package com.example.java_unittest_mitch.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class NoteTest
{
    @Test
    public void test_equal_notes() {
        Note note1=new Note(1,"title","content","time");
        Note note2=new Note(1,"title","content","time");
        assertEquals(note1,note2);
    }

    @Test
    public void test_areNotequal_notes_differnetId() {
        Note note1=new Note(1,"title","content","time");
        Note note2=new Note(2,"title","content","time");
        //assertFalse(note1==note2);
        assertNotEquals(note1,note2);
    }

    @Test
    public void test_equal_notes_withDifferent_timestamps() {
        Note note1=new Note(1,"title","content","time1");
        Note note2=new Note(1,"title","content","time2");
        assertEquals(note1,note2);
    }
    @Test
    public void test_areNotequal_notes_differnetTitle() {
        Note note1=new Note(1,"title1","content","time");
        Note note2=new Note(1,"title2","content","time");
        assertNotEquals(note1,note2);
    }
    @Test
    public void test_areNotequal_notes_differnetContent() {
        Note note1=new Note(1,"title","content1","time");
        Note note2=new Note(1,"title","content2","time");
        assertNotEquals(note1,note2);
    }

}