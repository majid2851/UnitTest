package com.codingwithmitch.unittesting2.models

import com.example.java_unittest_mitch.models.Note
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NoteTest
{
    private val TIME_1="1,1,2023"
    private val TIME_2="1,2,2023"
    /*Compare two equal notes*/
    @Test
    internal fun is_Notes_Are_Equal_when_they_have_equal_data()
    {
        //Arrange
        val note1= Note("majid","you are going to be successful",TIME_1)
        note1.id=1
        val note2=Note("majid","you are going to be successful",TIME_2)
        note2.id=2
        //Act

        //Assert
        Assertions.assertNotEquals(note1,note2)
    }
 

    //-----------------------------------------------------
    /*Compare notes with 2 different ids*/
    @Test
    internal fun isEqualFunc_returnFalse_when_ids_are_not_the_same()
    {
        val note1=Note("majid","is working in netherland",TIME_1)
        note1.id=1
        val note2=Note("majid","is working in netherland",TIME_2)
        note2.id=2

        Assertions.assertNotEquals(note1,note2)

    }
    //-----------------------------------------------------



    /*Compare notes with 2 different content*/
    @Test
    internal fun isEqualFunc_returnTrue_when_content_areNotTheSame()
    {
        val note1=Note("majid","is working in netherland",TIME_1)
        note1.id=1
        val note2=Note("majid","is working in netherland very well",TIME_2)
        note2.id=1

        Assertions.assertEquals(note1,note2)
    }

    //-----------------------------------------------------

    /*Compare notes with 2 differnt timestamp*/
    @Test
    internal fun isEqualFuct_returnTrue_when_timestampsAreDifferent()
    {
        val note1=Note("majid","is working in netherland",TIME_1)
        note1.id=1
        val note2=Note("majid","is working in netherland",TIME_2)
        note2.id=1

        Assertions.assertEquals(note1,note2)

    }

    //-----------------------------------------------------
    /*Compare notes with 2 different Title*/
    @Test
    internal fun isEqualFunc_returnFalse_when_titlesAreDifferent()
    {
        val note1=Note("majid2851","is working in netherland",TIME_1)
        note1.id=1
        val note2=Note("majid","is working in netherland",TIME_2)
        note2.id=1
        Assertions.assertNotEquals(note1,note2)
    }

    //-----------------------------------------------------




}