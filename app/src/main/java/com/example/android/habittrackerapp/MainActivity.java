package com.example.android.habittrackerapp;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittrackerapp.data.HabitClass.HabitEntry;

import com.example.android.habittrackerapp.data.HabitHelper;

public class MainActivity extends AppCompatActivity {
    private HabitHelper mHabitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mHabitHelper = new HabitHelper(MainActivity.this);
    }

    protected void onStart() {
        super.onStart();
        readHabit();
    }

    private Cursor readHabit() {
        SQLiteDatabase db = mHabitHelper.getReadableDatabase();
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_STATUS};
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.description);

        try {

            displayView.setText("The activity table has " + cursor.getCount() + " habits.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_STATUS + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int statusColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_STATUS);

            // Read the values in the cursor object
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentStatus = cursor.getInt(statusColumnIndex);
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentStatus ));
            }
        } finally {
            cursor.close();
        }
        return cursor;
    }
}
