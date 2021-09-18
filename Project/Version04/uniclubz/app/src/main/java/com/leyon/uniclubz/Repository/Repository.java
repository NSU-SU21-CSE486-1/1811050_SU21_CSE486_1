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
import com.google.firebase.database.Query;
import com.leyon.uniclubz.Entity.Club;
import com.leyon.uniclubz.Entity.Event;
import com.leyon.uniclubz.Entity.Student;

public class Repository {

    //Firebase Auth
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    //Firebase Realtime Database
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference studentsRef = rootRef.child("Students");
    DatabaseReference clubsRef = rootRef.child("Clubs");
    DatabaseReference eventsRef = rootRef.child("Events");

    FirebaseDatabaseLiveData firebaseDatabaseEventLiveData = new FirebaseDatabaseLiveData(eventsRef);
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

    public DatabaseReference getSignedInStudentUniversityAffiliations() {
        return studentsRef.child(getSignedInUserUID()).child("universityAffiliationList");
    }

    public Task<Void> addClubToDatabase(Club club) {
        DatabaseReference ref = clubsRef.push();
        String id = ref.getKey();
        club.setId(id);
        return ref.setValue(club);
    }

    public void removeClubFromDatabase(String clubID) {
        //search, find verify club owned by signed in student and then remove
    }

    public Query getSignedInStudentOwnedClubs() {
        Query query = clubsRef.orderByChild("clubOwnerUID").equalTo(getSignedInUserUID());
        return query;
    }

    public Task<Void> addEventToDatabase(Event event) {
        DatabaseReference ref = eventsRef.push();
        String id = ref.getKey();
        event.setId(id);
        return ref.setValue(event);
    }

    public void removeEventFromDatabase(String clubID) {
        //search, find verify event belongs to club owned by signed in student and then remove
    }

    public Query getEventsOfClub(String clubId) {
        Query query = eventsRef.orderByChild("eventOrganizingClubId").equalTo(clubId);
        return query;
    }

    /*public void getEventsOfClubsSignedInStudentIsMember() {
        //not possible to do this with firebase realtime database. need to use firestore instead :(
    }*/

    @NonNull
    public LiveData<DataSnapshot> getDatabaseEventLiveData() {
        return firebaseDatabaseEventLiveData;
    }

    @NonNull
    public LiveData<Boolean> getAuthLiveData() {
        return firebaseAuthLiveData;
    }


}
