package com.leyon.uniclubz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.leyon.uniclubz.Entity.Club;

import java.util.ArrayList;
import java.util.List;

public class ClubFragment_MyClubs_ClubRecyclerAdapter extends RecyclerView.Adapter<ClubFragment_MyClubs_ClubRecyclerAdapter.ViewHolder>{

    FragmentManager fragmentManager;

    List<Club> myClubs = new ArrayList<>();

    public ClubFragment_MyClubs_ClubRecyclerAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.myclub_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position;
        holder.clubRecyclerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to club admin page
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ClubFragment_ClubAdminFragment clubAdminFragment = ClubFragment_ClubAdminFragment.newInstance(myClubs.get(i));
                fragmentTransaction.replace(R.id.fragment_container, clubAdminFragment).addToBackStack(null).commit();
            }
        });

        holder.clubName.setText(myClubs.get(position).getClubName());

        if (myClubs.get(position).getClubMembersUIDList() != null) {
            holder.clubMemberCount.setText("Number of club members: "
                    + Integer.toString(myClubs.get(position).getClubMembersUIDList().size()));
        } else {
            holder.clubMemberCount.setText("Number of club members: "
                    + Integer.toString(0));
        }

        if (myClubs.get(position).getClubMembersUIDList() != null) {
            holder.clubMemberJoinRequestCount.setText("Club join requests: "
                    + Integer.toString(myClubs.get(position).getClubMemberJoinRequestUIDList().size()));
        } else {
            holder.clubMemberJoinRequestCount.setText("Club join requests: "
                    + Integer.toString(0));
        }

    }

    @Override
    public int getItemCount() {
        if (myClubs != null) {
            return myClubs.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout clubRecyclerItem; //for setting an onClickListener
        TextView clubName;
        TextView clubMemberCount;
        TextView clubMemberJoinRequestCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clubRecyclerItem = itemView.findViewById(R.id.clubRecyclerItem);
            clubName = itemView.findViewById(R.id.clubName);
            clubMemberCount = itemView.findViewById(R.id.clubMemberCount);
            clubMemberJoinRequestCount = itemView.findViewById(R.id.clubMemberJoinRequestCount);
        }
    }

    public void setClubList(List<Club> club){
        myClubs = club;
        notifyDataSetChanged();
    }
}
