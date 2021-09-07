package com.leyon.project03;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class Tab3 extends Fragment {

    public Tab3() {
        // Required empty public constructor
    }


    public static Tab3 newInstance() {
        Tab3 fragment = new Tab3();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitForm();
            }
        });
    }

    public void SubmitForm() {

        try {
            UserData newUserData = new UserData(); //save all data here then add it to DataStorage;

            //data from tab1
            LinearLayout tab1 = MainActivity.pagerAdapter.getFragment(1).getView().findViewById(R.id.tab1);
            EditText userNameText = tab1.findViewById(R.id.userNameText);
            DatePicker dateOfBirthPicker = tab1.findViewById(R.id.dateOfBirthPicker);
            EditText nidNumber = tab1.findViewById(R.id.nidNumber);
            EditText bloodGroupText = tab1.findViewById(R.id.bloodGroupText);

            //store data from tab1
            newUserData.setUserName(userNameText.getText().toString());
            newUserData.setDateOfBirth( dateOfBirthPicker.getDayOfMonth()
                    + "/" + dateOfBirthPicker.getMonth()
                    + "/" + dateOfBirthPicker.getYear() );
            newUserData.setNid(Integer.parseInt(nidNumber.getText().toString()));
            newUserData.setBloodGroup(bloodGroupText.getText().toString());

            //data from tab2
            LinearLayout tab2 = MainActivity.pagerAdapter.getFragment(2).getView().findViewById(R.id.uniFragmentHolder);

            int numOfUniAffiliations = UniversityInfoForm.numberOfUniInfoFormsCreated;

            for (int i=1; i <= numOfUniAffiliations; i++) {
                View uniV = tab2.findViewWithTag("UniData" + i); //tag format set in UniversityInfoForm fragment class

                Spinner universitySelectSpinner = uniV.findViewById(R.id.universitySelectSpinner);
                Spinner universityDepartmentSelectSpinner = uniV.findViewById(R.id.universityDepartmentSelectSpinner);
                Spinner universityStudyLevelSelectSpinner = uniV.findViewById(R.id.universityStudyLevelSelectSpinner);
                EditText studentIDNumber = uniV.findViewById(R.id.studentIDNumber);
                EditText universityEmail = uniV.findViewById(R.id.universityEmail);

                //store data from UniversityInfoForm fragment from tab2
                UniversityData newUniData = new UniversityData(
                        universitySelectSpinner.getSelectedItem().toString(),
                        universityDepartmentSelectSpinner.getSelectedItem().toString(),
                        universityStudyLevelSelectSpinner.getSelectedItem().toString(),
                        Integer.parseInt(studentIDNumber.getText().toString()),
                        universityEmail.getText().toString()
                );

                newUserData.addUniversityAffiliation(newUniData);
            }

            //data from tab3
            LinearLayout tab3 = MainActivity.pagerAdapter.getFragment(3).getView().findViewById(R.id.tab3);
            EditText userEmailText = tab3.findViewById(R.id.userEmailText);
            EditText userPhoneNumber = tab3.findViewById(R.id.userPhoneNumber);

            //store tab3 data
            newUserData.setPersonalEmail(userEmailText.getText().toString());
            newUserData.setPhoneNo( Integer.parseInt(userPhoneNumber.getText().toString()) );

            //show submitted
            //Toast.makeText(getContext(), "Data Submitted Sucessfully", Toast.LENGTH_SHORT).show();

            DataStorage.loadData(getContext());
            DataStorage.addUserData(newUserData);
            DataStorage.saveData(getContext());

            UniversityInfoForm.numberOfUniInfoFormsCreated = 0; //reset

            //go to recycler view to see all stored users
            Intent intent = new Intent(getContext(), UsersListActivity.class);
            startActivity(intent);
            getActivity().finish();

        } catch (Exception e) {
            Log.e("Submit", e.toString());
            //error caused when Integer.parse gets empty string from EditText
            Toast.makeText(getContext(), "Please fill up all forms", Toast.LENGTH_SHORT).show();
        }
    }
}