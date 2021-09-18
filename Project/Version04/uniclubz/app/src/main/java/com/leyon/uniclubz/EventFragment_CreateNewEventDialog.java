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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.leyon.uniclubz.Entity.Event;


public class EventFragment_CreateNewEventDialog extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public EventFragment_CreateNewEventDialog() {
        // Required empty public constructor
    }

    public static EventFragment_CreateNewEventDialog newInstance(String param1, String param2) {
        EventFragment_CreateNewEventDialog fragment = new EventFragment_CreateNewEventDialog();
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
        return inflater.inflate(R.layout.fragment_event__create_new_event_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView createEventClubName = view.findViewById(R.id.createEventClubName);
        createEventClubName.setText("Create New Event - " + mParam1);

        EditText createEventName = view.findViewById(R.id.createEventName);
        DatePicker createEventDate = view.findViewById(R.id.createEventDate);
        TimePicker createEventTime = view.findViewById(R.id.createEventTime);
        Button createEventButton = view.findViewById(R.id.createEventButton);

        RadioGroup createEventRadioGroup = view.findViewById(R.id.createEventRadioGroup);
        boolean isPublicRadioButton;
        switch (createEventRadioGroup.getCheckedRadioButtonId()) {
            case(R.id.createEventRadioButtonPublic):
                isPublicRadioButton = true;
                break;
            case(R.id.createEventRadioButtonNotPublic):
                isPublicRadioButton = false;
                break;
            default:
                isPublicRadioButton = false;
        }


        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Event newEvent = new Event();

                newEvent.setEventName(createEventName.getText().toString());

                newEvent.setEventDate(createEventDate.getDayOfMonth()
                        + "/" + createEventDate.getMonth()
                        + "/" + createEventDate.getYear());

                int hour12 = createEventTime.getHour();
                if (createEventTime.getHour() == 12 || createEventTime.getHour() == 0) {
                    hour12 = 12;
                } else {
                    hour12 = (createEventTime.getHour() >= 12 ? createEventTime.getHour()-12 : createEventTime.getHour());
                }
                newEvent.setEventTime( hour12 + ":"
                        + createEventTime.getMinute()
                        + (createEventTime.getHour() >= 12 ? " pm" : " am") );

                newEvent.setEventOrganizingClubId(mParam2);

                newEvent.setPublic(isPublicRadioButton);

                //send to database
                MainActivity.mViewModel.addEventToDatabase(newEvent).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Event Added", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                });
            }
        });
    }
}