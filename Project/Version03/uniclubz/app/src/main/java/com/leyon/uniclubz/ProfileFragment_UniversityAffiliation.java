package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leyon.uniclubz.Entity.UniversityAffiliation;

public class ProfileFragment_UniversityAffiliation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private UniversityAffiliation mParam1;

    public ProfileFragment_UniversityAffiliation() {
        // Required empty public constructor
    }

    public static ProfileFragment_UniversityAffiliation newInstance(UniversityAffiliation param1) {
        ProfileFragment_UniversityAffiliation fragment = new ProfileFragment_UniversityAffiliation();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (UniversityAffiliation) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile__university_affiliation, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mParam1 <- University Affiliation item
        TextView profileView_universityName = view.findViewById(R.id.profileView_universityName);
        TextView profileView_studyLevel = view.findViewById(R.id.profileView_studyLevel);
        TextView profileView_department = view.findViewById(R.id.profileView_department);
        TextView profileView_universityEmail = view.findViewById(R.id.profileView_universityEmail);
        TextView profileView_studentID = view.findViewById(R.id.profileView_studentID);

        profileView_universityName.setText("University Name: " + mParam1.getUniversityName());
        profileView_studyLevel.setText("University Name: " + mParam1.getStudyLevel());
        profileView_department.setText("University Name: " + mParam1.getDepartment());
        profileView_universityEmail.setText("University Name: " + mParam1.getUniversityEmail());
        profileView_studentID.setText("University Name: " + mParam1.getStudentID());
    }
}