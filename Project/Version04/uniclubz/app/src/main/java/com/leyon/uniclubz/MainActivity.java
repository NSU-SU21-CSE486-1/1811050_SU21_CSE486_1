package com.leyon.uniclubz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leyon.uniclubz.ViewModel.AppViewModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static AppViewModel mViewModel;

    //use this to change fragments
    FragmentManager fragmentManager = getSupportFragmentManager();

    Button loginButton;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(AppViewModel.class);

        loginButton = findViewById(R.id.loginButton);


        mViewModel.getAuthLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    loginButton.setText("Log Out");
                } else {
                    loginButton.setText("Log In");
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        //set home fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new EventFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mViewModel.test();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(item.getItemId()) {
            case(R.id.homeMenu):
                drawer.closeDrawer(GravityCompat.START);
                fragmentTransaction.replace(R.id.fragment_container, new EventFragment()).addToBackStack(null).commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;

            case(R.id.profileMenu):
                if (mViewModel.isStudentSignedIn()) {
                    drawer.closeDrawer(GravityCompat.START);
                    fragmentTransaction.replace(R.id.fragment_container, new ProfileFragment()).addToBackStack(null).commit();
                } else {
                    Toast.makeText(this, "Please Log In First", Toast.LENGTH_SHORT).show();
                }
                return true;

            case(R.id.clubMenu):
                if (mViewModel.isStudentSignedIn()) {
                    drawer.closeDrawer(GravityCompat.START);
                    fragmentTransaction.replace(R.id.fragment_container, new ClubFragment()).addToBackStack(null).commit();
                } else {
                    Toast.makeText(this, "Please Log In First", Toast.LENGTH_SHORT).show();
                }
                return true;

            case(R.id.exitMenu):
                finish();
                System.exit(0);
                return true;
        }

        return false;
    }

    public void logIn(View view) {
        drawer.closeDrawer(GravityCompat.START);

        if (!mViewModel.isStudentSignedIn()) {
            LogInFragment logInFragment = LogInFragment.newInstance("");
            logInFragment.show(getSupportFragmentManager(), "LoginFragment");
        } else {
            mViewModel.signOutStudent();
        }
    }
}