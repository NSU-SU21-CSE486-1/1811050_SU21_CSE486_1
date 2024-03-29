package com.leyon.uniclubz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.leyon.uniclubz.Entity.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventFragmentAdapter extends RecyclerView.Adapter<EventFragmentAdapter.ViewHolder> {

    FragmentManager fragmentManager;
    List<Event> eventsList = new ArrayList<>();

    public EventFragmentAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.event_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position;
        holder.eventRecyclerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open DialogFragment to join the event
            }
        });

        holder.eventName.setText(eventsList.get(position).getEventName());
        holder.eventClubName.setText("Club: " + eventsList.get(position).getEventOrganizingClubId());
        MainActivity.mViewModel.getClubNameByID(eventsList.get(position).getEventOrganizingClubId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.d("EventClubName", snapshot.toString());
                //holder.eventClubName.setText("Club: " + );
                HashMap<String, HashMap<String, Object>> eventMap = (HashMap<String, HashMap<String, Object>>) snapshot.getValue();
                for (String c :eventMap.keySet()) {
                    HashMap<String, Object> x = eventMap.get(c);

                    holder.eventClubName.setText("Club: " + x.get("clubName"));
                    break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.eventDate.setText("Event Date: " + eventsList.get(position).getEventDate());
        holder.eventTime.setText("Event Start Time: " + eventsList.get(position).getEventTime());
    }

    @Override
    public int getItemCount() {
        if (eventsList != null) {
            return eventsList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout eventRecyclerItem;

        TextView eventName;
        TextView eventClubName;
        TextView eventDate;
        TextView eventTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventRecyclerItem = itemView.findViewById(R.id.eventRecyclerItem);
            eventName = itemView.findViewById(R.id.eventName);
            eventClubName = itemView.findViewById(R.id.eventClubName);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventTime = itemView.findViewById(R.id.eventTime);
        }
    }

    public void setEventsList(List<Event> events) {
        eventsList = events;
        notifyDataSetChanged();
    }
}
