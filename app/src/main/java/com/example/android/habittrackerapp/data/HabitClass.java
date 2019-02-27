package com.example.android.habittrackerapp.data;
import android.provider.BaseColumns;

public class HabitClass {

    private HabitClass() {
    }

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "Habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_HABIT_STATUS = "status";
        public static final int HABIT_DONE = 1;
        public static final int HABIT_NOTDONE = 0;
    }

}
