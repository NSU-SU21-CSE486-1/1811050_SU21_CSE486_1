package com.leyon.project03;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class UserInfoDialog_UniDetail extends Fragment {

    TextView detailsUniversityName;
    TextView detailsUniversityDepartment;
    TextView detailsUniversityStudyLevel;
    TextView detailsUniversityEmail;
    TextView detailsStudentID;

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // Rename and change types of parameters
    private UniversityData universityData;


    public static UserInfoDialog_UniDetail newInstance(UniversityData param1) {
        UserInfoDialog_UniDetail fragment = new UserInfoDialog_UniDetail();

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    public UserInfoDialog_UniDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            universityData = (UniversityData) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info_dialog__uni_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailsUniversityName = view.findViewById(R.id.detailsUniversityName);
        detailsUniversityDepartment = view.findViewById(R.id.detailsUniversityDepartment);
        detailsUniversityStudyLevel = view.findViewById(R.id.detailsUniversityStudyLevel);
        detailsUniversityEmail = view.findViewById(R.id.detailsUniversityEmail);
        detailsStudentID = view.findViewById(R.id.detailsStudentID);

        detailsUniversityName.setText(universityData.getUniversityName());
        detailsUniversityDepartment.setText(universityData.getDepartment());
        detailsUniversityStudyLevel.setText(universityData.getStudyLevel());
        detailsUniversityEmail.setText(universityData.getUniversityEmail());
        detailsStudentID.setText(Integer.toString(universityData.getStudentID()));

    }
}