package com.leyon.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView passageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        passageTextView = findViewById(R.id.passageTextView);

        Intent intent = getIntent();
        String passage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        passageTextView.setText(passage);
    }

    public void goBack(View view) {
        Intent replyIntent = new Intent();
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
