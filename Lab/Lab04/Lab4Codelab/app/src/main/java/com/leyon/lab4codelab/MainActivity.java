package com.leyon.lab4codelab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTextView = findViewById(R.id.dateText);
    }

    public void ShowTimePicker(View view) {
        TimePickerFragment timePicker = new TimePickerFragment();

        timePicker.show(getSupportFragmentManager(), getString(R.string.timepicker));
    }

    public void processTimePicker(int hour, int minute) {
        dateTextView.setText( "Time: " + (hour > 12 ? hour-12 : hour) + ":"
                            + minute
                            + (hour > 12 ? " pm" : " am") );
    }
}