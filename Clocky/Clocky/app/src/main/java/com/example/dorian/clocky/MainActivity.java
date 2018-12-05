package com.example.dorian.clocky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void createAlarm(View activity_main) {
        setContentView(R.layout.alarmcreation);
    }
    public void gotoAlarm(View activity_main) {
        setContentView(R.layout.activity_main);
    }
    public void gotoSound(View activity_main) {
        setContentView(R.layout.sounds);
    }
    public void gotoSetting(View activity_main) {
        setContentView(R.layout.settings);
    }
    public void cancelMethod(View alarmcreation) {
        setContentView(R.layout.activity_main);
    }
    public void saveMethod(View alarmcreation) {
        int[] days = new int[7];
        ToggleButton mon = findViewById(R.id.mondayButton);
        ToggleButton tues = findViewById(R.id.tuesdayButton);
        ToggleButton wed = findViewById(R.id.wednesdayButton);
        ToggleButton thur = findViewById(R.id.thursdayButton);
        ToggleButton fri = findViewById(R.id.fridayButton);
        ToggleButton sat = findViewById(R.id.saturdayButton);
        ToggleButton sun = findViewById(R.id.sundayButton);
        if (mon.isChecked()) {
            days[0] = 1;
        } else {
            days[0] = 0;
        }
        if (tues.isChecked()) {
            days[1] = 1;
        } else {
            days[1] = 0;
        }
        if (wed.isChecked()) {
            days[2] = 1;
        } else {
            days[2] = 0;
        }
        if (thur.isChecked()) {
            days[3] = 1;
        } else {
            days[3] = 0;
        }
        if (fri.isChecked()) {
            days[4] = 1;
        } else {
            days[4] = 0;
        }
        if (sat.isChecked()) {
            days[5] = 1;
        } else {
            days[5] = 0;
        }
        if (sun.isChecked()) {
            days[6] = 1;
        } else {
            days[6] = 0;
        }
        TimePicker time = findViewById(R.id.simpleTimePicker);
        int hour = time.getCurrentHour();
        int minute = time.getCurrentMinute();
        String amOrPm = "am";
        if (hour == 12) {
            amOrPm = "pm";
        } else if (hour > 12) {
            hour = hour - 12;
            amOrPm = "pm";
        }
        setContentView(R.layout.activity_main);
    }
    public void alarmPanel() {

    }
}
