package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.leyon.uniclubz.Entity.Student;
import com.leyon.uniclubz.Entity.UniversityAffiliation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends DialogFragment {

    private int pageNumber = 1;
    private int maxPages = 4;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout page1 = view.findViewById(R.id.page1);
        LinearLayout page2 = view.findViewById(R.id.page2);
        LinearLayout page3 = view.findViewById(R.id.page3);

        Button backButton = view.findViewById(R.id.backButton);
        Button nextButton = view.findViewById(R.id.nextButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber > 1 && pageNumber <= 3) {
                    pageNumber--;
                }
                switch (pageNumber) {
                    case 1:
                        page1.setVisibility(View.VISIBLE);
                        page2.setVisibility(View.GONE);
                        page3.setVisibility(View.GONE);
                        break;
                    case 2:
                        page1.setVisibility(View.GONE);
                        page2.setVisibility(View.VISIBLE);
                        page3.setVisibility(View.GONE);
                        break;
                    case 3:
                        page1.setVisibility(View.GONE);
                        page2.setVisibility(View.GONE);
                        page3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber >= 1 && pageNumber < 3) {
                    pageNumber++;
                }
                switch (pageNumber) {
                    case 1:
                        page1.setVisibility(View.VISIBLE);
                        page2.setVisibility(View.GONE);
                        page3.setVisibility(View.GONE);
                        break;
                    case 2:
                        page1.setVisibility(View.GONE);
                        page2.setVisibility(View.VISIBLE);
                        page3.setVisibility(View.GONE);
                        break;
                    case 3:
                        page1.setVisibility(View.GONE);
                        page2.setVisibility(View.GONE);
                        page3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        Button addUniAffiliationButton = view.findViewById(R.id.addUniAffiliationButton);
        addUniAffiliationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.signupUniFragmentHolder, new SignUpFragment_UniAffiliation());
                fragmentTransaction.commit();
            }
        });

        Button submitSignUp = view.findViewById(R.id.signUpButton_SignUpFragment);
        submitSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parse data from form and add to database
                try {
                    Student newUserData = new Student(); //save all data here then add it to database;

                    //data from page1
                    EditText userNameText = page1.findViewById(R.id.userNameText);
                    DatePicker dateOfBirthPicker = page1.findViewById(R.id.dateOfBirthPicker);
                    EditText nidNumber = page1.findViewById(R.id.nidNumber);
                    EditText bloodGroupText = page1.findViewById(R.id.bloodGroupText);

                    //store data from page1
                    newUserData.setName(userNameText.getText().toString());
                    newUserData.setDateOfBirth( dateOfBirthPicker.getDayOfMonth()
                            + "/" + dateOfBirthPicker.getMonth()
                            + "/" + dateOfBirthPicker.getYear() );
                    newUserData.setNid(Integer.parseInt(nidNumber.getText().toString()));
                    newUserData.setBloodGroup(bloodGroupText.getText().toString());

                    //data from page2
                    int numOfUniAffiliations = SignUpFragment_UniAffiliation.numberOfUniInfoFormsCreated;

                    for (int i=1; i <= numOfUniAffiliations; i++) {
                        View uniV = page2.findViewWithTag("UniData" + i); //tag format set in SignUpFragment class

                        Spinner universitySelectSpinner = uniV.findViewById(R.id.universitySelectSpinner);
                        Spinner universityDepartmentSelectSpinner = uniV.findViewById(R.id.universityDepartmentSelectSpinner);
                        Spinner universityStudyLevelSelectSpinner = uniV.findViewById(R.id.universityStudyLevelSelectSpinner);
                        EditText studentIDNumber = uniV.findViewById(R.id.studentIDNumber);
                        EditText universityEmail = uniV.findViewById(R.id.universityEmail);

                        //store data from UniversityInfoForm fragment from page2
                        UniversityAffiliation newUniData = new UniversityAffiliation(
                                universitySelectSpinner.getSelectedItem().toString(),
                                universityStudyLevelSelectSpinner.getSelectedItem().toString(),
                                universityDepartmentSelectSpinner.getSelectedItem().toString(),
                                universityEmail.getText().toString(),
                                Integer.parseInt(studentIDNumber.getText().toString())
                        );

                        newUserData.addUniversityAffiliation(newUniData);
                    }

                    //data from page3
                    EditText userEmailText = page3.findViewById(R.id.userEmailText);
                    EditText userPhoneNumber = page3.findViewById(R.id.userPhoneNumber);

                    //store page3 data
                    newUserData.setPersonalEmail(userEmailText.getText().toString());
                    newUserData.setPhoneNumber( Integer.parseInt(userPhoneNumber.getText().toString()) );

                    EditText password = view.findViewById(R.id.userPassword);
                    String pass = password.getText().toString();

                    //call viewmodel in mainactivity and add student to database
                    MainActivity.mViewModel.signUpStudent(newUserData.getPersonalEmail(), pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                MainActivity.mViewModel.signInStudent(newUserData.getPersonalEmail(), pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        MainActivity.mViewModel.addStudentDetailsToDatabase(newUserData);
                                        Toast.makeText(getActivity().getApplicationContext(), "Sign Up Complete!", Toast.LENGTH_SHORT).show();
                                        dismiss();
                                    }
                                });
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e("Submit", e.toString());
                    //error caused when Integer.parse gets empty string from EditText
                    Toast.makeText(getContext(), "Please fill up all forms", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}