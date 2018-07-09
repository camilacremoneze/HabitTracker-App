package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.habittracker.data.HabitContract.*;

import java.sql.SQLClientInfoException;

/**
 * Created by ccremoneze on 5/12/18.
 */

public final class HabitDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Tracker.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                    HabitEntry._ID + " INTEGER PRIMARY KEY," +
                    HabitEntry.COLUMN_HABIT_ACTIVITY + " TEXT," +
                    HabitEntry.COLUMN_HABIT_BENEFITS + " TEXT," +
                    HabitEntry.COLUMN_HABIT_PERIOD + " TEXT," +
                    HabitEntry.COLUMN_HABIT_HOUR + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
