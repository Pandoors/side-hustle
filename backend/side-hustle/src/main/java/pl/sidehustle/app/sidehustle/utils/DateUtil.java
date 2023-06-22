package pl.sidehustle.app.sidehustle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(Date date) {
        String pattern = "dd-MM-yyyy_HHmm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return date != null ? simpleDateFormat.format(date) : "__.__.____";
    }

    public static Date parseDate(String dateString) throws ParseException {
        String pattern = "dd-MM-yyyy_HHmm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return dateString != null ? simpleDateFormat.parse(dateString) : null;
    }

}
