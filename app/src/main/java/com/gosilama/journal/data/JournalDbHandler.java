package com.gosilama.journal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.gosilama.journal.model.Constants.DATABASE_NAME;
import static com.gosilama.journal.model.Constants.DATABASE_VERSION;
import static com.gosilama.journal.model.Constants.KEY_ID;
import static com.gosilama.journal.model.Constants.KEY_JOURNAL_ENTRY;
import static com.gosilama.journal.model.Constants.KEY_JOURNAL_TITLE;
import static com.gosilama.journal.model.Constants.KEY_TIME_CREATED;
import static com.gosilama.journal.model.Constants.TABLE_NAME;

public class JournalDbHandler extends SQLiteOpenHelper{

    public JournalDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL Scripts
        // CREATE TABLE
        String CREATE_JOURNAL_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + "INTEGER PRIMARY KEY,"
                + KEY_JOURNAL_TITLE + " TEXT,"
                + KEY_JOURNAL_ENTRY + " BLOB,"
                + KEY_TIME_CREATED + " LONG"
                + ")";

        db.execSQL(CREATE_JOURNAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
