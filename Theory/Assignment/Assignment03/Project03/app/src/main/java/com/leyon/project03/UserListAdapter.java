package com.leyon.project03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    FragmentManager fragmentManager;

    public UserListAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.userlist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position; //cuz using position directly inside onclick was giving error
        holder.userListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), holder.userName.getText().toString(), Toast.LENGTH_SHORT).show();


                UserInfoDialog detailsDialog = UserInfoDialog.newInstance(DataStorage.getUserList().get(i));
                detailsDialog.show(fragmentManager, "UserDetails");
            }
        });

        holder.userName.setText("Name: " + DataStorage.getUserList().get(position).userName);
        holder.userPhoneNo.setText("Phone: " + Integer.toString(DataStorage.getUserList().get(position).phoneNo));
    }

    @Override
    public int getItemCount() {
        return DataStorage.getNumberOfUserDataStored();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout userListItem;
        TextView userName;
        TextView userPhoneNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userListItem = itemView.findViewById(R.id.userListItem);
            userName = itemView.findViewById(R.id.recyclerview_UserName);
            userPhoneNo = itemView.findViewById(R.id.recyclerview_PhoneNo);
        }
    }

}
