package com.codingwithmitch.unittesting2.util

import com.example.java_unittest_mitch.util.DateUtil.*
import org.junit.jupiter.api.*

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random

class DateUtilTest
{
    private val today="01-2023"
    /*to be sure that exception of date-format is not going to be created*/
    @Test
    internal fun notThrowException_when_dateFormat_isCorrect()
    {
        assertDoesNotThrow()
        {
            Assertions.assertEquals(today,  getCurrentTimeStamp())
        }
    }

    //-------------------------------------------------------------------

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    internal fun monthNumbersAreCorrect(monthNumber:Int,
                    testInfo: TestInfo,testReporter:TestReporter)
    {
        Assertions.assertEquals(months[monthNumber], getMonthFromNumber(monthNumbers[monthNumber]))

    }
    //----------------------------------------------------------------------
    @ParameterizedTest
    @ValueSource(ints = [1,2,3,4,5,6,7,8,9,10,11,12])
    internal fun returnError_when_positions_isNotCorrect(number:Int)
    {
        val randomNumber= Random.nextInt(90)+12
        Assertions.assertEquals(getMonthFromNumber(randomNumber.toString()),GET_MONTH_ERROR)

    }



}