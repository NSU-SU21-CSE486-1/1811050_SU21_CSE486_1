package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.textview);

        try {
            int i = 1 / 0;
        } catch (Exception e) {
            Log.e("MainActivity", "Cannot divide by zero!");
        }
    }

    public void Toast(View view) {
        Toast.makeText(this, "Happy Birthday to Omuk!", Toast.LENGTH_LONG).show();
    }

    public void Count(View view) {
        count++;
        textview.setText(Integer.toString(count));
    }
}
