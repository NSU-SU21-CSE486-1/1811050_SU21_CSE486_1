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
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.leyon.uniclubz.Entity.Club;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchClubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchClubFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchClubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchClubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchClubFragment newInstance(String param1, String param2) {
        SearchClubFragment fragment = new SearchClubFragment();
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
        return inflater.inflate(R.layout.fragment_search_club, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText searchText = view.findViewById(R.id.searchClubEditText);
        Button searchButton = view.findViewById(R.id.searchClubButton);

        RecyclerView searchResultRecyclerView = view.findViewById(R.id.searchClubRecyclerView);
        searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SearchClubFragmentAdapter adapter = new SearchClubFragmentAdapter(getParentFragmentManager());
        searchResultRecyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mViewModel.searchForClub(searchText.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Log.d("search", snapshot.getValue().toString());
                        //clubName  clubUniversity id=-MjjqG0RQkTe3MHd_4Zs, clubOwnerUID
                        HashMap<String, HashMap<String, Object>> eventMap = (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
                        List<Club> clubList = new ArrayList<>();
                        for (String c : eventMap.keySet()) {
                            HashMap<String, Object> x = eventMap.get(c);

                            Club club = new Club();
                            club.setId((String) x.get("id"));
                            club.setClubName((String) x.get("clubName"));
                            club.setClubUniversity((String) x.get("clubUniversity"));
                            club.setClubOwnerUID((String) x.get("clubOwnerUID"));
                            club.setClubMembersUIDList((List<String>) x.get("clubMembersUIDList"));
                            club.setClubMemberJoinRequestUIDList((List<String>) x.get("clubMemberJoinRequestUIDList"));

                            clubList.add(club);
                        }

                        adapter.setSearchClubList(clubList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}