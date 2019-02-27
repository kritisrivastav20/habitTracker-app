package com.example.android.habittrackerapp.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittrackerapp.data.HabitClass.HabitEntry;

public class HabitHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "habits.db";
    private static final int DATABASE_VERSION = 1;

    public HabitHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " + HabitEntry.COLUMN_HABIT_STATUS + " INTEGER NOT NULL);";
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
