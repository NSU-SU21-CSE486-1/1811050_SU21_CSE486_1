package com.leyon.uniclubz.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leyon.uniclubz.Entity.Student;

public class Repository {

    //Firebase Auth
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    //Firebase Realtime Database
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference studentsRef = rootRef.child("Students");
    DatabaseReference clubsRef = rootRef.child("Clubs");
    DatabaseReference eventsRef = rootRef.child("Events");

    FirebaseDatabaseLiveData firebaseDatabaseLiveData = new FirebaseDatabaseLiveData(eventsRef);
    FirebaseAuthLiveData firebaseAuthLiveData = new FirebaseAuthLiveData(firebaseAuth);

    public Repository() {
        //empty constructor
    }

    //authentication

    public Task<AuthResult> signUpStudent(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    public boolean isStudentSignedIn() {
        boolean loggedIn  = (firebaseAuth.getCurrentUser() != null ); //not null means signed in
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
            //delete user
            firebaseAuth.getCurrentUser().delete(); //delete signed in user
            //delete user data clubs, events
        }
    }

    //@NonNull
    public String getSignedInUserUID() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            return user.getUid();
        } else {
            return null;
        }
    }

    //database

    public void addStudentDetailsToDatabase(Student student) {
        if (isStudentSignedIn()) {
            DatabaseReference usrRef = studentsRef.child(getSignedInUserUID());
            student.setId(firebaseAuth.getUid());
            usrRef.setValue(student);
        }
    }

    public void deleteStudentDetailsFromDatabase() {
        if (isStudentSignedIn()) {
            String studentUID = getSignedInUserUID();
            //studentsRef.child(studentUID).removeValue();
        }
    }

    public DatabaseReference getSignedInStudent() {
        return studentsRef.child(getSignedInUserUID());
    }

    @NonNull
    public LiveData<DataSnapshot> getDatabaseLiveData() {
        return firebaseDatabaseLiveData;
    }

    @NonNull
    public LiveData<Boolean> getAuthLiveData() {
        return firebaseAuthLiveData;
    }


}
