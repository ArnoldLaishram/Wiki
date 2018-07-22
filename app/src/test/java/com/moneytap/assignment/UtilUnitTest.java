package com.moneytap.assignment;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilUnitTest {

    @Test
    public void getDayOfWeekFromDate_isCorrect() {
        String dayofWeekFromDate = Util.getDayofWeekFromDate("2018-06-12");
        assertEquals(dayofWeekFromDate, "Tuesday");
    }

    @Test
    public void getDayOfWeekFromDate_isInCorrect() {
        String dayofWeekFromDate = Util.getDayofWeekFromDate("2018/06/12");
        assertEquals(dayofWeekFromDate, null);
    }

}