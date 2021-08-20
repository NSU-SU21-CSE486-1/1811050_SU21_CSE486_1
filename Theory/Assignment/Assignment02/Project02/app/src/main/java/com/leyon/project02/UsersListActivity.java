package com.leyon.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

public class UsersListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListAdapter = new UserListAdapter(getSupportFragmentManager());

        recyclerView.setAdapter(userListAdapter);
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        //finish();
        startActivity(intent);
    }*/

    public void deleteUsersData(View view) {
        DataStorage.deleteUserData(this);
        userListAdapter.notifyDataSetChanged();
    }
}