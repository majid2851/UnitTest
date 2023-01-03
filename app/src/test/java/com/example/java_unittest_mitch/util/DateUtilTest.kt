package com.codingwithmitch.unittesting2.util

import android.util.Log
import com.example.java_unittest_mitch.testNote
import com.example.java_unittest_mitch.util.DateUtil
import com.example.java_unittest_mitch.util.DateUtil.*
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.TestReporter
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.log
import kotlin.random.Random

class DateUtilTest
{
    private val today="01-2023"
    private val note= testNote
    /*to be sure that exception of date-format is not going to be created*/
    @Test
    internal fun notThrowException_when_dateFormat_isCorrect()
    {
        assertDoesNotThrow()
        {
            Assert.assertEquals(today, DateUtil.getCurrentTimeStamp())
        }
    }
    //-------------------------------------------------------------------

    @ParameterizedTest
    @ValueSource(ints = [0,1,2,3,4,5,6,7,8,9,10,11,])
    internal fun monthNumbersAreCorrect(monthNumber:Int,
                    testInfo: TestInfo,testReporter:TestReporter)
    {
        Assert.assertEquals(months[monthNumber], getMonthFromNumber(monthNumbers[monthNumber]))

    }
    //----------------------------------------------------------------------
    @ParameterizedTest
    @ValueSource(ints = [1,2,3,4,5,6,7,8,9,10,11,12])
    internal fun returnError_when_positions_isNotCorrect(number:Int)
    {
        val randomNumber= Random.nextInt(90)+12
        Assert.assertEquals(getMonthFromNumber(randomNumber.toString()),GET_MONTH_ERROR)

    }



}