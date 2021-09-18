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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.leyon.uniclubz.Entity.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventFragment extends Fragment {

    public EventFragment() {
        // Required empty public constructor
    }

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView eventRecyclerView = view.findViewById(R.id.eventsRecyclerView); //show all events here
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EventFragmentAdapter adapter = new EventFragmentAdapter(getParentFragmentManager());
        eventRecyclerView.setAdapter(adapter);

        MainActivity.mViewModel.getDatabaseEventLiveData().observe(getActivity(), new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                //Toast.makeText(getContext(), "event added and observer fired", Toast.LENGTH_SHORT).show();

                HashMap<String, HashMap<String, Object>> eventMap = (HashMap<String, HashMap<String, Object>>) dataSnapshot.getValue();
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
        });
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}