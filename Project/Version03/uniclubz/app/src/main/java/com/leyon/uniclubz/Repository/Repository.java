package com.leyon.uniclubz.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Repository {

    private boolean loggedIn = false;

    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference users = root.child("Users");
    DatabaseReference clubs = root.child("Clubs");
    DatabaseReference events = root.child("Events");
    FirebaseAppLiveData firebaseAppLiveData = new FirebaseAppLiveData(root); //for observe LiveData

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public Repository() {
        //empty constructor
    }

    public boolean signUpStudent(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).isSuccessful();
    }

    public boolean isStudentSignedIn() {
        loggedIn = (firebaseAuth.getCurrentUser() != null ); //not null means signed in
        return loggedIn;
    }

    public Task<AuthResult> signInStudent(String email, String password) {
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }

    public void signOutStudent() {
        firebaseAuth.signOut();
    }

    public void deleteCurrentUser() {
        if (isStudentSignedIn()) {
            firebaseAuth.getCurrentUser().delete(); //delete signed in user
        }
    }

    //database

    @NonNull
    public LiveData<DataSnapshot> getLiveData() {
        return firebaseAppLiveData;
    }


}
