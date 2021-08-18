package com.leyon.project02;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UniversityInfoForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UniversityInfoForm extends Fragment {

    public static int numberOfUniInfoFormsCreated = 0;

    public UniversityInfoForm() {
        // Required empty public constructor
    }


    public static UniversityInfoForm newInstance() {
        UniversityInfoForm fragment = new UniversityInfoForm();
        numberOfUniInfoFormsCreated++;
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
        return inflater.inflate(R.layout.fragment_university_info_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner universitySelectSpinner = view.findViewById(R.id.universitySelectSpinner);
        ArrayAdapter<CharSequence> uniNameAdapter = ArrayAdapter.createFromResource(getContext(), R.array.university_list, android.R.layout.simple_spinner_item);
        uniNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universitySelectSpinner.setAdapter(uniNameAdapter);

        Spinner universityDepartmentSelectSpinner = view.findViewById(R.id.universityDepartmentSelectSpinner);
        ArrayAdapter<CharSequence> uniDepartmentAdapter = ArrayAdapter.createFromResource(getContext(), R.array.departments, android.R.layout.simple_spinner_item);
        uniDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universityDepartmentSelectSpinner.setAdapter(uniDepartmentAdapter);

        Spinner universityStudyLevelSelectSpinner = view.findViewById(R.id.universityStudyLevelSelectSpinner);
        ArrayAdapter<CharSequence> uniStudyLevelAdapter = ArrayAdapter.createFromResource(getContext(), R.array.study_levels, android.R.layout.simple_spinner_item);
        uniStudyLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universityStudyLevelSelectSpinner.setAdapter(uniStudyLevelAdapter);
    }
}