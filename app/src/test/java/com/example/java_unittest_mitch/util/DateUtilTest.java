package com.example.java_unittest_mitch.util;

import static com.example.java_unittest_mitch.util.DateUtil.monthNumbers;
import static com.example.java_unittest_mitch.util.DateUtil.months;
import static org.junit.jupiter.api.Assertions.*;

import androidx.core.widget.TintableImageSourceView;

import com.example.java_unittest_mitch.models.Note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.SimpleDateFormat;
import java.util.Date;


class DateUtilTest {
    private static final String today="04-2022";

    @Test
    public void getCurrentTime() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(today,DateUtil.getCurrentTimeStamp());
            }
        });
    }
    //way simple but not enough for testing a parameters of a list
    @Test
    public void test_getMonthFromNumber() {
        assertEquals(months[3],DateUtil.getMonthFromNumber("04"));
    }

    //best way,parameterized way
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    public void test_monthFromNumberForAllPossibleValues(int monthNumber
            , TestInfo testInfo, TestReporter testReporter){
        assertEquals(months[monthNumber],DateUtil.getMonthFromNumber(monthNumbers[monthNumber]));
    }




}