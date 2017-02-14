package com.sombre.shop.utils.dateTimeParser;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by inna on 14.02.17.
 */
public class DateTimeParser {

    public static DateTime parse(String date, String pattern) {

        return DateTime.parse(date,
                DateTimeFormat.forPattern(pattern));
    }
}
