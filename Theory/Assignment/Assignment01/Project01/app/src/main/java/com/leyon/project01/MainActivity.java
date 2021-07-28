package com.leyon.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int currentSignUpPageNo = 1;
    int maxPages = 4;

    //pages
    LinearLayout signUpPage1;
    LinearLayout signUpPage2;
    LinearLayout signUpPage3;
    LinearLayout signUpPage4;

    Button submitButton;

    //info page1
    EditText userNameText;
    DatePicker dateOfBirthPicker;
    EditText nidNumber;
    EditText bloodGroupText;
    //info page2
    Spinner universitySelectSpinner;
    Spinner universityDepartmentSelectSpinner;
    Spinner universityStudyLevelSelectSpinner;
    EditText studentIDNumber;
    //info page3
    EditText userEmailText;
    EditText userPhoneNumber;

    //review info
    TextView reviewName;
    TextView reviewDateOfBirth;
    TextView reviewNIDNumber;
    TextView reviewBloodGroup;
    TextView reviewUniversityName;
    TextView reviewUniversityDepartment;
    TextView reviewUniversityStudyLevel;
    TextView reviewStudentID;
    TextView reviewEmail;
    TextView reviewPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpPage1 = findViewById(R.id.signUpPage1);
        signUpPage2 = findViewById(R.id.signUpPage2);
        signUpPage3 = findViewById(R.id.signUpPage3);
        signUpPage4 = findViewById(R.id.signUpPage4);

        submitButton = findViewById(R.id.submitButton);

        userNameText = findViewById(R.id.userNameText);
        dateOfBirthPicker = findViewById(R.id.dateOfBirthPicker);
        nidNumber = findViewById(R.id.nidNumber);
        bloodGroupText = findViewById(R.id.bloodGroupText);

        universitySelectSpinner = findViewById(R.id.universitySelectSpinner);
        ArrayAdapter<CharSequence> uniNameAdapter = ArrayAdapter.createFromResource(this, R.array.university_list, android.R.layout.simple_spinner_item);
        uniNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universitySelectSpinner.setAdapter(uniNameAdapter);

        universityDepartmentSelectSpinner = findViewById(R.id.universityDepartmentSelectSpinner);
        ArrayAdapter<CharSequence> uniDepartmentAdapter = ArrayAdapter.createFromResource(this, R.array.departments, android.R.layout.simple_spinner_item);
        uniDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universityDepartmentSelectSpinner.setAdapter(uniDepartmentAdapter);

        universityStudyLevelSelectSpinner = findViewById(R.id.universityStudyLevelSelectSpinner);
        ArrayAdapter<CharSequence> uniStudyLevelAdapter = ArrayAdapter.createFromResource(this, R.array.study_levels, android.R.layout.simple_spinner_item);
        uniStudyLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universityStudyLevelSelectSpinner.setAdapter(uniStudyLevelAdapter);

        studentIDNumber = findViewById(R.id.studentIDNumber);

        userEmailText = findViewById(R.id.userEmailText);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);

        reviewName = findViewById(R.id.reviewName);
        reviewDateOfBirth = findViewById(R.id.reviewDateOfBirth);
        reviewNIDNumber = findViewById(R.id.reviewNIDNumber);
        reviewBloodGroup = findViewById(R.id.reviewBloodGroup);
        reviewUniversityName = findViewById(R.id.reviewUniversityName);
        reviewUniversityDepartment = findViewById(R.id.reviewUniversityDepartment);
        reviewUniversityStudyLevel = findViewById(R.id.reviewUniversityStudyLevel);
        reviewStudentID = findViewById(R.id.reviewStudentID);
        reviewEmail = findViewById(R.id.reviewEmail);
        reviewPhoneNumber = findViewById(R.id.reviewPhoneNumber);

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
                submitButton.setText("Submit");

                reviewName.setText("Name: " + userNameText.getText().toString());
                reviewDateOfBirth.setText("Date of Birth: "
                            + dateOfBirthPicker.getDayOfMonth()
                            + "/" + dateOfBirthPicker.getMonth()
                            + "/" + dateOfBirthPicker.getYear()
                        );
                reviewNIDNumber.setText("NID: " + nidNumber.getText().toString());
                reviewBloodGroup.setText("Blood Group: " + bloodGroupText.getText().toString());
                reviewUniversityName.setText("University: " + universitySelectSpinner.getSelectedItem().toString());
                reviewUniversityDepartment.setText("Department: " + universityDepartmentSelectSpinner.getSelectedItem().toString());
                reviewUniversityStudyLevel.setText("Study Level: " + universityStudyLevelSelectSpinner.getSelectedItem().toString());
                reviewStudentID.setText("Student ID: " + studentIDNumber.getText().toString());
                reviewEmail.setText("Email: " + userEmailText.getText().toString());
                reviewPhoneNumber.setText("Phone Number: " + userPhoneNumber.getText().toString());
                break;
            case 5:
                //submit data after click submit button
                Toast.makeText(this, "Signup Complete!", Toast.LENGTH_SHORT).show();
                submitButton.setOnClickListener(null); //to prevent duplicate submissions
                //currentSignUpPageNo = 0; //just for testing page change to first page. comment out above line to use this
                break;
        }

    }
}