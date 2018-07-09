package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.android.habittracker.data.HabitContract.*;

import com.example.android.habittracker.data.HabitDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
        insertHabits();
        displayDatabaseInfo();
    }

    private void insertHabits(){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        /* Force Bruta only to declare lines on the table */
        /* 1st line */
        values.put(HabitEntry.COLUMN_HABIT_ACTIVITY, "Take Vitamins");
        values.put(HabitEntry.COLUMN_HABIT_BENEFITS, "Help imunologic sytem");
        values.put(HabitEntry.COLUMN_HABIT_PERIOD, HabitEntry.PERIOD_MORNING);
        values.put(HabitEntry.COLUMN_HABIT_HOUR, "8am");
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        if(newRowId == -1)
        {
            return;
        }
        else
        {
            /* 2nc line */
            values.put(HabitEntry.COLUMN_HABIT_ACTIVITY, "Yoga exercises");
            values.put(HabitEntry.COLUMN_HABIT_BENEFITS, "Yoga teaches you how to control breathing, increase concentration, " +
                                                         "flexibility and strength");
            values.put(HabitEntry.COLUMN_HABIT_PERIOD, HabitEntry.PERIOD_MORNING);
            values.put(HabitEntry.COLUMN_HABIT_HOUR, "8:30am");
            db.insert(HabitEntry.TABLE_NAME, null, values);
        }
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_ACTIVITY,
                HabitEntry.COLUMN_HABIT_BENEFITS,
                HabitEntry.COLUMN_HABIT_PERIOD,
                HabitEntry.COLUMN_HABIT_HOUR
        };


        //Cursor cursor = db.rawQuery("SELECT * FROM " + HabitEntry.TABLE_NAME, null );
        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        TextView displayView = (TextView) findViewById(R.id.text_view_habits);

        try {
            displayView.setText("The habits table cointains " + cursor.getCount() + " habits Tracker.\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_ACTIVITY + " - " +
                    HabitEntry.COLUMN_HABIT_BENEFITS + " - " +
                    HabitEntry.COLUMN_HABIT_PERIOD + " - " +
                    HabitEntry.COLUMN_HABIT_HOUR);

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int activityColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_ACTIVITY);
            int benefitsColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_BENEFITS);
            int periodColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_PERIOD);
            int hourColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_HOUR);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(activityColumnIndex);
                String currentBenefits = cursor.getString(benefitsColumnIndex);
                int currentPeriod = cursor.getInt(periodColumnIndex);
                int currentHour = cursor.getInt(hourColumnIndex);

                displayView.append("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBenefits + " - " +
                        currentPeriod + " - " +
                        currentHour);
            }

        } finally {
            cursor.close();
        }
    }
}
