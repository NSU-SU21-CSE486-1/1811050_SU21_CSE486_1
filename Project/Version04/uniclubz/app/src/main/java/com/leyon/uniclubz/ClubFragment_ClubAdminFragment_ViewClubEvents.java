package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.leyon.uniclubz.Entity.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClubFragment_ClubAdminFragment_ViewClubEvents extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public ClubFragment_ClubAdminFragment_ViewClubEvents() {
        // Required empty public constructor
    }

    public static ClubFragment_ClubAdminFragment_ViewClubEvents newInstance(String param1) {
        ClubFragment_ClubAdminFragment_ViewClubEvents fragment = new ClubFragment_ClubAdminFragment_ViewClubEvents();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club__club_admin_fragment__view_club_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView adminClubViewEventRecyclerView = view.findViewById(R.id.adminClubViewEventRecyclerView);
        adminClubViewEventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EventFragmentAdapter adapter = new EventFragmentAdapter(getParentFragmentManager());
        adminClubViewEventRecyclerView.setAdapter(adapter);

        MainActivity.mViewModel.getEventsOfClub(mParam1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, HashMap<String, Object>> eventMap = (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
                List<Event> eventList = new ArrayList<>();

                for (String c : eventMap.keySet()) {
                    HashMap<String, Object> x = eventMap.get(c);
                    Event newEvent = new Event();
                    newEvent.setEventName((String) x.get("eventName"));
                    newEvent.setEventDate((String) x.get("eventDate"));
                    newEvent.setEventTime((String) x.get("eventTime"));
                    newEvent.setPublic((Boolean) x.get("public"));
                    newEvent.setId((String) x.get("id"));
                    newEvent.setEventOrganizingClubId((String) x.get("eventOrganizingClubId"));

                    eventList.add(newEvent);
                }

                adapter.setEventsList(eventList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}