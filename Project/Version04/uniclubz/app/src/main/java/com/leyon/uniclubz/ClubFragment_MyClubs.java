package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.leyon.uniclubz.Entity.Club;
import com.leyon.uniclubz.Entity.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClubFragment_MyClubs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubFragment_MyClubs extends Fragment {

    ClubFragment_MyClubs_ClubRecyclerAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClubFragment_MyClubs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubFragment_MyClubs.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubFragment_MyClubs newInstance(String param1, String param2) {
        ClubFragment_MyClubs fragment = new ClubFragment_MyClubs();
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
        return inflater.inflate(R.layout.fragment_club__my_clubs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button myClubCreateNewClubButtom = view.findViewById(R.id.myClubCreateNewClubButtom);
        myClubCreateNewClubButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClubFragment_MyClubs_CreateNewClubDialog createNewClubDialog = ClubFragment_MyClubs_CreateNewClubDialog.newInstance("","");
                createNewClubDialog.show(getParentFragmentManager(), "CreateClub");
            }
        });

        RecyclerView myClubRecyclerView = view.findViewById(R.id.myClubRecyclerView);
        myClubRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ClubFragment_MyClubs_ClubRecyclerAdapter(getParentFragmentManager());
        myClubRecyclerView.setAdapter(adapter);

        addClubsToAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        addClubsToAdapter();
    }

    private void addClubsToAdapter() {
        MainActivity.mViewModel.getSignedInStudentOwnedClubs().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, HashMap<String, Object>> clubMap = (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
                List<Club> clubList = new ArrayList<>();

                for (String c: clubMap.keySet()) {
                    HashMap<String, Object> x = clubMap.get(c);
                    Club club = new Club();
                    club.setClubName((String) x.get("clubName"));
                    club.setClubUniversity((String) x.get("clubUniversity"));
                    club.setClubOwnerUID((String) x.get("clubOwnerUID"));
                    club.setClubMembersUIDList((List<String>) x.get("clubMembersUIDList"));
                    club.setClubEvents((List<Event>) x.get("clubEvents"));
                    club.setClubEvents((List<Event>) x.get("clubMemberJoinRequestUIDList"));

                    clubList.add(club);
                }

                adapter.setClubList(clubList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}