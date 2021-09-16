package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.leyon.uniclubz.Entity.Student;
import com.leyon.uniclubz.Entity.UniversityAffiliation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView profileView_name = view.findViewById(R.id.profileView_name);
        TextView profileView_dateOfBirth = view.findViewById(R.id.profileView_dateOfBirth);
        TextView profileView_nid = view.findViewById(R.id.profileView_nid);
        TextView profileView_bloodGroup = view.findViewById(R.id.profileView_bloodGroup);
        //LinearLayout profileView_UniversityAffiliationFragmentHolder = view.findViewById(R.id.profileView_UniversityAffiliationFragmentHolder);
        TextView profileView_personalEmail = view.findViewById(R.id.profileView_personalEmail);
        TextView profileView_phoneNumber = view.findViewById(R.id.profileView_phoneNumber);

        MainActivity.mViewModel.getSignedInStudent().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);

                profileView_name.setText("Name: " + student.getName());
                profileView_dateOfBirth.setText("Date of Birth: " + student.getDateOfBirth());
                profileView_nid.setText("NID no: " + Integer.toString(student.getNid()));
                profileView_bloodGroup.setText("BloodGroup: " + student.getBloodGroup());

                for (UniversityAffiliation u : student.getUniversityAffiliationList()) {
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ProfileFragment_UniversityAffiliation itemUniAffiliation = ProfileFragment_UniversityAffiliation.newInstance(u);
                    fragmentTransaction.add(R.id.profileView_UniversityAffiliationFragmentHolder, itemUniAffiliation);
                    fragmentTransaction.commit();
                }

                profileView_personalEmail.setText("Personal Email: " + student.getPersonalEmail());
                profileView_phoneNumber.setText("Phone No: " + Integer.toString(student.getPhoneNumber()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}