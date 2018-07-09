package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by ccremoneze on 5/12/18.
 */

public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private HabitContract() {}

    /* Inner class that defines the table contents */
    public static class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";
        public static final String COLUMN_ID = BaseColumns._ID;

        public static final String COLUMN_HABIT_ACTIVITY = "activity";
        public static final String COLUMN_HABIT_BENEFITS = "benefits"; // motivation: what kind of benefits this activity brings to the user
        /* user can define activity period or precisely hour, or both */
        public static final String COLUMN_HABIT_PERIOD = "period"; // morning (6am - 12am), afternoon (12am - 6pm) - , night (6pm - 12pm)
        public static final String COLUMN_HABIT_HOUR = "hour";

        /**
         * Possible values for the period.
         */
        public static final int PERIOD_UNKNOWN = 0;
        public static final int PERIOD_MORNING = 1;
        public static final int PERIOD_AFTERNOON = 2;
        public static final int PERIOD_NIGHT = 3;
    }
}
