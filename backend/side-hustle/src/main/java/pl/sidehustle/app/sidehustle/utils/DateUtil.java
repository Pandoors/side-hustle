package pl.sidehustle.app.sidehustle.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(Date date) {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return date != null ? simpleDateFormat.format(date) : "__.__.____";
    }

}
