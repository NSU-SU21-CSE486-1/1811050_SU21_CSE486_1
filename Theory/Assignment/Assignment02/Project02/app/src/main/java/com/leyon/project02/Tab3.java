package com.leyon.project02;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

        LinearLayout tab1 = MainActivity.pagerAdapter.getFragment(1).getView().findViewById(R.id.tab1);
        EditText userNameText = tab1.findViewById(R.id.userNameText);

        LinearLayout tab2 = MainActivity.pagerAdapter.getFragment(2).getView().findViewById(R.id.uniFragmentHolder);
        View uniV = tab2.findViewWithTag("UniData" + UniversityInfoForm.numberOfUniInfoFormsCreated);
        EditText id = uniV.findViewById(R.id.studentIDNumber);
        //Boolean.toString(uniV==null)
        //Integer.toString(UniversityInfoForm.numberOfUniInfoFormsCreated)

        Toast.makeText(getContext(), userNameText.getText().toString(), Toast.LENGTH_SHORT).show();

    }
}