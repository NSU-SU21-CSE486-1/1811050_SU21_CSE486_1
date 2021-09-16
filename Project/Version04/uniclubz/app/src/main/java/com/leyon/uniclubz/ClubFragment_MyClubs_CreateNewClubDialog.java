package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.leyon.uniclubz.Entity.Club;
import com.leyon.uniclubz.Entity.UniversityAffiliation;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClubFragment_MyClubs_CreateNewClubDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubFragment_MyClubs_CreateNewClubDialog extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClubFragment_MyClubs_CreateNewClubDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubFragment_MyClubs_CreateNewClubDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubFragment_MyClubs_CreateNewClubDialog newInstance(String param1, String param2) {
        ClubFragment_MyClubs_CreateNewClubDialog fragment = new ClubFragment_MyClubs_CreateNewClubDialog();
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
        return inflater.inflate(R.layout.fragment_club__my_clubs__create_new_club_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button clubCreateButton = view.findViewById(R.id.clubCreateButton);
        Spinner newClubUniversity = view.findViewById(R.id.newClubUniversity);
        List<String> studentAffiliatedUniversities = new ArrayList<>(); //get from firebase
        MainActivity.mViewModel.getSignedInStudentUniversityAffiliations().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<List<UniversityAffiliation>> t = new GenericTypeIndicator<List<UniversityAffiliation>>() {};
                List<UniversityAffiliation> universityAffiliationList = snapshot.getValue(t);

                for (UniversityAffiliation universityAffiliation: universityAffiliationList) {
                    studentAffiliatedUniversities.add(universityAffiliation.getUniversityName());

                    //students can only create club for universities they are affiliated with
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, studentAffiliatedUniversities);
                    newClubUniversity.setAdapter(spinnerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        clubCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newClubName = view.findViewById(R.id.newClubName);
                Club club = new Club();
                club.setClubName(newClubName.getText().toString());

                Club newClub = new Club();
                newClub.setClubName(newClubName.getText().toString());
                newClub.setClubOwnerUID(MainActivity.mViewModel.getSignedInUserUID());
                newClub.setClubUniversity(newClubUniversity.getSelectedItem().toString());

                //send newClub to firebase
                MainActivity.mViewModel.addClubToDatabase(newClub).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Successfully created new club!", Toast.LENGTH_SHORT).show();
                            dismiss();
                        } else {
                            Toast.makeText(getContext(), "Error! Creating club failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}