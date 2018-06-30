package com.gosilama.journal.util;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void showReadableDate() {
        Long currentTime = System.currentTimeMillis();
        String expectedDate = DateFormat.getDateTimeInstance()
                .format(new Date(currentTime).getTime());

        String returnedDate = Utils.showReadableDate(currentTime);

        assertEquals(expectedDate, returnedDate);

    }
}