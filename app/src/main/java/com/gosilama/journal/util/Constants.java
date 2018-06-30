package com.gosilama.journal.util;

public final class Constants {
    private Constants(){ }

    // Database
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "journal.db";
    public static final String TABLE_NAME = "journals";

    // Database test
    public static final String TEST_DATABASE_NAME = "test_journal.db";
    public static final int TEST_DATABASE_VERSION = 1;

    // journals table column names
    public static final String KEY_ID = "id";
    public static final String KEY_JOURNAL_TITLE = "journal_title";
    public static final String KEY_JOURNAL_ENTRY = "journal_entry";
    public static final String KEY_TIME_CREATED = "time_created";
    public static final String KEY_USER_ID = "user_id";

    public static String CURRENT_USER_ID = "";
}