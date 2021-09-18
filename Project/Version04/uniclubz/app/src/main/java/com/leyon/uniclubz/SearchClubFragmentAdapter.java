package com.leyon.uniclubz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leyon.uniclubz.Entity.Club;

import java.util.ArrayList;
import java.util.List;

public class SearchClubFragmentAdapter extends RecyclerView.Adapter<SearchClubFragmentAdapter.ViewHolder> {

    FragmentManager fragmentManager;
    List<Club> clubList = new ArrayList<>();

    public SearchClubFragmentAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_club_recycer_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.searchClubName.setText(clubList.get(position).getClubName());
    }

    @Override
    public int getItemCount() {
        if (clubList != null) {
            return clubList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView searchClubName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchClubName = itemView.findViewById(R.id.searchClubName);
        }
    }

    public void setSearchClubList(List<Club> clubs) {
        clubList = clubs;
        notifyDataSetChanged();
    }
}
