package com.leyon.project03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;
import com.leyon.project03.ViewModel.StudentUniversityViewModel;

import java.util.List;

public class UsersListActivity extends AppCompatActivity {

    //new user create intent request code
    public static final int CREATENEWUSERREQCODE = 0;

    RecyclerView recyclerView;
    UserListAdapter userListAdapter;

    public static StudentUniversityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        DataStorage.loadData(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userListAdapter = new UserListAdapter(getSupportFragmentManager());

        recyclerView.setAdapter(userListAdapter);

        viewModel = new ViewModelProvider(this).get(StudentUniversityViewModel.class);

        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users) {
                // Update the cached copy of the words in the adapter.
                userListAdapter.setUsers(users);
            }
        });
    }

    public void deleteUsersData(View view) {
        //DataStorage.deleteUserData(this);
        //userListAdapter.notifyDataSetChanged();
        viewModel.deleteAllUserData();
        viewModel.deleteAllUniversityAffiliationData();
    }

    public void createNewUser(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, CREATENEWUSERREQCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATENEWUSERREQCODE && resultCode == RESULT_OK) {
            Bundle result = data.getExtras(); //data sent from intent
            User user = (User) result.getSerializable(MainActivity.USERDATA);
            UniversityAffiliation[] uniAffiliationArray = (UniversityAffiliation[]) result.getSerializable(MainActivity.UNIVERSITYDATA);

            viewModel.insertUserAndUniversityAffiliations(user, uniAffiliationArray);
        }
    }
}