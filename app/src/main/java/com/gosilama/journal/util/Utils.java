package com.gosilama.journal.util;

import java.text.DateFormat;
import java.util.Date;

public final class Utils {
    public static String showReadableDate(Long dateInMillis) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(new Date(dateInMillis).getTime());
    }
}
