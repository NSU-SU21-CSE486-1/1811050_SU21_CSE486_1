package com.leyon.lab6codelab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Spinner colorSpinner;

    // Shared preferences object
    private SharedPreferences mPreferences;

    // Name of shared preferences file
    private String sharedPrefFile;

    // Key for current count
    private String COUNT_KEY;
    // Key for current color
    private String COLOR_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPrefFile = getString(R.string.sharedPrefs);

        COUNT_KEY = getString(R.string.COUNT_KEY);
        COLOR_KEY = getString(R.string.COLOR_KEY);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        colorSpinner = findViewById(R.id.colorSpinner);


    }

    public void reset(View view) {
        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    public void changeBackground(View view) {

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        switch (colorSpinner.getSelectedItem().toString()) {
            case "Red":
                preferencesEditor.putInt(COLOR_KEY, getColor(R.color.red_background));
                break;
            case "Green":
                preferencesEditor.putInt(COLOR_KEY, getColor(R.color.green_background));
                break;
            case "Blue":
                preferencesEditor.putInt(COLOR_KEY, getColor(R.color.blue_background));
                break;
            case "Black":
                preferencesEditor.putInt(COLOR_KEY, getColor(R.color.black));
                break;
        }

        Toast.makeText(this,
                "Saved " + colorSpinner.getSelectedItem().toString() + " color for background",
                Toast.LENGTH_SHORT).show();

        preferencesEditor.apply();
    }
}