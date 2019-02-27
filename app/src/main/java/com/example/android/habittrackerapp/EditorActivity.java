package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.android.habittrackerapp.data.HabitClass;
import com.example.android.habittrackerapp.data.HabitHelper;


public class EditorActivity extends AppCompatActivity {
    EditText activityName;
    Spinner statusName;
    private int mstatus = HabitClass.HabitEntry.HABIT_NOTDONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        activityName = (EditText) findViewById(R.id.activity_name);
        statusName = (Spinner) findViewById(R.id.statusMode);

        setupSpinner();
    }

    private void setupSpinner() {
        String[] test = new String[]{"Pending", "Completed"};
        ArrayAdapter<String> statusArray = new ArrayAdapter<String>(EditorActivity.this, android.R.layout.simple_spinner_item, test);
        statusArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusName.setAdapter(statusArray);

        statusName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Pending")) {
                        mstatus = HabitClass.HabitEntry.HABIT_NOTDONE;
                    } else {
                        mstatus = HabitClass.HabitEntry.HABIT_DONE;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mstatus = HabitClass.HabitEntry.HABIT_NOTDONE;
            }
        });
    }

    private void insertHabit() {
        String nameString = activityName.getText().toString().trim();
        HabitHelper mDbHelper = new HabitHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitClass.HabitEntry.COLUMN_HABIT_NAME, nameString);
        values.put(HabitClass.HabitEntry.COLUMN_HABIT_STATUS, mstatus);
        long newRowId = db.insert(HabitClass.HabitEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertHabit();
                finish();
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
