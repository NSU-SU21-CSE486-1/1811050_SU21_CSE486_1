package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.leyon.uniclubz.Entity.Club;

public class ClubFragment_ClubAdminFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private Club mParam1;

    public ClubFragment_ClubAdminFragment() {
        // Required empty public constructor
    }

    public static ClubFragment_ClubAdminFragment newInstance(Club param1) {
        ClubFragment_ClubAdminFragment fragment = new ClubFragment_ClubAdminFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Club) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club__club_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView adminClubName = view.findViewById(R.id.adminClubName);
        Button adminCreateNewEventButton = view.findViewById(R.id.adminCreateNewEventButton);
        Button adminViewCreatedEvents = view.findViewById(R.id.adminViewCreatedEvents);
        Button adminViewMemberList = view.findViewById(R.id.adminViewMemberList);
        Button adminViewMemberJoinRequests = view.findViewById(R.id.adminViewMemberJoinRequests);
        Button adminDeleteClub = view.findViewById(R.id.adminDeleteClub);

        adminClubName.setText(mParam1.getClubName());
    }
}