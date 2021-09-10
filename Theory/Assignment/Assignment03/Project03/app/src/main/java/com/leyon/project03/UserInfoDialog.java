package com.leyon.project03;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;

import java.util.List;

public class UserInfoDialog extends DialogFragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // Rename and change types of parameters
    private User userData;


    TextView detailsName;
    TextView detailsDateOfBirth;
    TextView detailsNIDNumber;
    TextView reviewBloodGroup;

    TextView detailsPersonalEmail;
    TextView detailsPhoneNumber;

    public UserInfoDialog() {
        // Required empty public constructor
    }

    public static UserInfoDialog newInstance(User param1) {
        UserInfoDialog fragment = new UserInfoDialog();

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userData = (User) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailsName = view.findViewById(R.id.detailsName);
        detailsDateOfBirth = view.findViewById(R.id.detailsDateOfBirth);
        detailsNIDNumber = view.findViewById(R.id.detailsNIDNumber);
        reviewBloodGroup = view.findViewById(R.id.reviewBloodGroup);

        detailsPersonalEmail = view.findViewById(R.id.detailsPersonalEmail);
        detailsPhoneNumber = view.findViewById(R.id.detailsPhoneNumber);

        //set details
        detailsName.setText("Name: " + userData.getName());
        detailsDateOfBirth.setText("DOB: " + userData.getDob());
        detailsNIDNumber.setText("NID: " + Integer.toString(userData.getNid()));
        reviewBloodGroup.setText("Blood Group: " + userData.getBloodGroup());

        LinearLayout uniDetailsHolder = view.findViewById(R.id.detailsUniAffiliationHolder);
        Toast.makeText(getContext(), Boolean.toString(uniDetailsHolder==null), Toast.LENGTH_SHORT).show();

        List<UniversityAffiliation> uniList =  UsersListActivity.viewModel.getUniversityAffiliationByUserId(userData.getId());

        if (uniList != null) {
            for (int i=0; i<uniList.size(); i++) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UserInfoDialog_UniDetail uniDetail = UserInfoDialog_UniDetail.newInstance(uniList.get(i));
                fragmentTransaction.add(R.id.detailsUniAffiliationHolder,uniDetail);
                fragmentTransaction.commit();
            }
        }


        detailsPersonalEmail.setText("Personal Email: " + userData.getPersonalEmail());
        detailsPhoneNumber.setText("Phone No: " + Integer.toString(userData.getPhoneNumber()));


    }
}