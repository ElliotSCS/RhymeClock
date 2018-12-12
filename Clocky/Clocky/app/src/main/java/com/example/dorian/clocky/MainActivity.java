package com.example.dorian.clocky;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mashape.unirest.http.JsonNode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    AlarmManager alarmMgr;
    PendingIntent alarmIntent;
    int[] days;
    int hour;
    int minute;
    String amOrPm;
    boolean alarmSet = false;
    String[] words = {"pie", "bye", "chair", "mouse", "headset", "girl", "boy", "woman", "man", "television", "laptop", "course",
            "assistant", "developer", "birthday", "final", "library", "table"};
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
        alarmPanel(days, hour, minute, amOrPm);
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
        days = new int[7];
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
        hour = time.getCurrentHour();
        minute = time.getCurrentMinute();
        amOrPm = "AM";
        if (hour == 12) {
            amOrPm = "PM";
        } else if (hour > 12) {
            hour = hour - 12;
            amOrPm = "PM";
        }
        setContentView(R.layout.activity_main);
        alarmSet = true;
        alarmPanel(days, hour, minute, amOrPm);
    }
    public void alarmPanel(int[] days, int hour, int minute, String AP) {
        if (!(alarmSet)) {
            return;
        }
        ConstraintLayout sample = findViewById(R.id.Sample_Alarm);
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
        Context context = getApplicationContext();
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (AP.equals("PM") && hour != 12) {
            hour+=12;
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 30, alarmIntent);
    }
    public class AlarmReceiver {
        public void onReceive() {
            setContentView(R.layout.activity_main);
        }
    }
    public void turnAlarmOff(View activity_main) {
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
    }
    public void skip() {
        TextView thing = findViewById(R.id.wordToRhyme);
        thing.setText(words[(int) (Math.random() * (words.length - 1))]);
    }
    public void skipThing(View rhymegame) {
        skip();
    }
    public RequestQueue requestQueue;
    /*public void checkRhyme(ArrayList<String> input) {
        EditText otherThing = findViewById(R.id.editText);
        if (input.contains(otherThing.getText())) {
            System.out.println("SEND RYHME IS WORKING");
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
            ProgressBar yup = findViewById(R.id.progressBar);
            if (yup.getProgress() == 0) {
                yup.setProgress(33);
                skip();
            } else if (yup.getProgress() == 33) {
                yup.setProgress(66);
                skip();
            } else {
                yup.setProgress(100);
            }
        }
    }*/

    public void sendRhyme(final View activity_main) {
        TextView thing = findViewById(R.id.wordToRhyme);
        EditText otherThing = findViewById(R.id.editText);
        soundsSimilar(thing.getText().toString());
        if (true/*soundsSimilar(thing.getText().toString()).contains(otherThing.getText())*/) {
            System.out.println("SEND RYHME IS WORKING");
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
            ProgressBar yup = findViewById(R.id.progressBar);
            if (yup.getProgress() == 0) {
                yup.setProgress(33);
                skip();
            } else if (yup.getProgress() == 33) {
                yup.setProgress(66);
                skip();
            } else {
                yup.setProgress(100);
            }
        }
    }
    public void gotoSound(final View activity_main) {
        setContentView(R.layout.rhymegame);
        final EditText edit = findViewById(R.id.editText);
        TextView rhymeWord = findViewById(R.id.wordToRhyme);
        int whichWord = (int) (Math.random()*(words.length - 1));
        rhymeWord.setText(words[whichWord]);
        requestQueue = Volley.newRequestQueue(this);
    }
    ArrayList<String> toReturn = new ArrayList<>();
    JSONArray thing;
    //THIS IS ALL FROM A DIFFERENT APP.
    public List<String> soundsSimilar(String word) {
        String s = word.replaceAll(" ", "+");
        String url = ("http://api.datamuse.com/words?rel_rhy=" + s);
        toReturn = new ArrayList<>();
        final int test = 1;
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        thing = response;

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.toString());
            }
        }
        );
        requestQueue.add(getRequest);
        //
        try {
            String toAdd;
            for (int i = 0; i < thing.length(); i++) {
                toAdd = (String)((JSONObject) thing.get(i)).get("word");
                //System.out.println(toAdd);
                toReturn.add(toAdd);
                //System.out.println(toReturn);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        //System.out.println(thing);
        System.out.println(toReturn);
        return toReturn;
    }
    private String getJSON(String url) {
        URL datamuse;
        URLConnection dc;
        StringBuilder s = null;
        try {
            datamuse = new URL(url);
            dc = datamuse.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(dc.getInputStream(), "UTF-8"));
            String inputLine;
            s = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                s.append(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s != null ? s.toString() : null;
    }
}
