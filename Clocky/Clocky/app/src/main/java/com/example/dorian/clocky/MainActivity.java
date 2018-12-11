package com.example.dorian.clocky;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    public boolean firstAlarm = true;
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
    public void soundSet(View sounds) {
        //ACTION_RINGTONE_PICKER()
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
        String amOrPm = "AM";
        if (hour == 12) {
            amOrPm = "PM";
        } else if (hour > 12) {
            hour = hour - 12;
            amOrPm = "PM";
        }
        setContentView(R.layout.activity_main);
        alarmPanel(days, hour, minute, amOrPm);
    }
    public void alarmPanel(int[] days, int hour, int minute, String AP) {
        ConstraintLayout sample = findViewById(R.id.Sample_Alarm);
        if (firstAlarm) {
            final TextView thing = findViewById(R.id.alarmTime);
            String insert = "";
            if (minute < 10) {
                insert = "0";
            }
            thing.setText(hour + ":" + insert + minute + " " + AP);
            //hour + ":" + minute + " " + AP
            Switch blah = findViewById(R.id.OnOffSwitch);
            blah.setChecked(true);
            if (days[0] == 1) {
                ToggleButton mon = findViewById(R.id.MondayToggle);
                mon.setChecked(true);
            }
            if (days[1] == 1) {
                ToggleButton tues = findViewById(R.id.tuesdayToggle);
                tues.setChecked(true);
            }
            if (days[2] == 1) {
                ToggleButton wed = findViewById(R.id.wednesdayToggle);
                wed.setChecked(true);
            }
            if (days[3] == 1) {
                ToggleButton thurs = findViewById(R.id.thursdayToggle);
                thurs.setChecked(true);
            }
            if (days[4] == 1) {
                ToggleButton fri = findViewById(R.id.fridayToggle);
                fri.setChecked(true);
            }
            if (days[5] == 1) {
                ToggleButton sat = findViewById(R.id.saturdayToggle);
                sat.setChecked(true);
            }
            if (days[6] == 1) {
                ToggleButton sun = findViewById(R.id.sundayToggle);
                sun.setChecked(true);
            }
        }
        else {
            //ConstraintLayout creating = new ConstraintLayout(sample);
        }
        //firstAlarm = false;
    }
}
