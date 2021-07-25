package com.leyon.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    int currentSignUpPageNo = 1;
    int maxPages = 4;

    LinearLayout signUpPage1;
    LinearLayout signUpPage2;
    LinearLayout signUpPage3;
    LinearLayout signUpPage4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpPage1 = findViewById(R.id.signUpPage1);
        signUpPage2 = findViewById(R.id.signUpPage2);
        signUpPage3 = findViewById(R.id.signUpPage3);
        signUpPage4 = findViewById(R.id.signUpPage4);
    }

    public void GoToNext(View view) {
        //switch pages by changing visibility until visit last page. then submit data.
        currentSignUpPageNo++;

        switch (currentSignUpPageNo) {
            case 1:
                signUpPage1.setVisibility(View.VISIBLE);
                signUpPage2.setVisibility(View.GONE);
                signUpPage3.setVisibility(View.GONE);
                signUpPage4.setVisibility(View.GONE);
                break;
            case 2:
                signUpPage1.setVisibility(View.GONE);
                signUpPage2.setVisibility(View.VISIBLE);
                signUpPage3.setVisibility(View.GONE);
                signUpPage4.setVisibility(View.GONE);
                break;
            case 3:
                signUpPage1.setVisibility(View.GONE);
                signUpPage2.setVisibility(View.GONE);
                signUpPage3.setVisibility(View.VISIBLE);
                signUpPage4.setVisibility(View.GONE);
                break;
            case 4:
                signUpPage1.setVisibility(View.GONE);
                signUpPage2.setVisibility(View.GONE);
                signUpPage3.setVisibility(View.GONE);
                signUpPage4.setVisibility(View.VISIBLE);
                break;
            default:
                currentSignUpPageNo = 1; //just for testing page change
        }

    }
}