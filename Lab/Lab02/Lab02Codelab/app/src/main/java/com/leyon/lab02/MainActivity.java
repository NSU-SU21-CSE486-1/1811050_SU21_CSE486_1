package com.leyon.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.leyon.lab02.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readPassage1 (View view) {
        //Toast.makeText(this, "testing button", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SecondActivity.class);
        String passage = getString(R.string.passage1);
        intent.putExtra(EXTRA_MESSAGE, passage);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void readPassage2 (View view) {
        //Toast.makeText(this, "testing button", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SecondActivity.class);
        String passage = getString(R.string.passage2);
        intent.putExtra(EXTRA_MESSAGE, passage);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void readPassage3 (View view) {
        //Toast.makeText(this, "testing button", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SecondActivity.class);
        String passage = getString(R.string.passage3);
        intent.putExtra(EXTRA_MESSAGE, passage);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}
