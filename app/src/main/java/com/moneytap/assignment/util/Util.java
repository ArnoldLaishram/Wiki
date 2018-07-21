package com.moneytap.assignment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getDayofWeekFromDate(String dateString) {
        try {
            // Get Date from String
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

            // get Day of the week from Date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
            return simpleDateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
