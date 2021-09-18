package com.leyon.uniclubz.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.leyon.uniclubz.Entity.Club;
import com.leyon.uniclubz.Entity.Event;
import com.leyon.uniclubz.Entity.Student;
import com.leyon.uniclubz.Repository.Repository;

public class AppViewModel extends AndroidViewModel {

    Repository repo;

    public AppViewModel(@NonNull Application application) {
        super(application);

        repo = new Repository();
    }

    //LiveData
    public LiveData<DataSnapshot> getDatabaseEventLiveData() {
        return repo.getDatabaseEventLiveData();
    }

    public LiveData<Boolean> getAuthLiveData() {
        return repo.getAuthLiveData();
    }

    //Auth
    public Task<AuthResult> signUpStudent(String email, String password) {
        return repo.signUpStudent(email, password);
    }

    public boolean isStudentSignedIn() {
        return repo.isStudentSignedIn();
    }

    public Task<AuthResult> signInStudent(String email, String password) {
        return repo.signInStudent(email, password);
    }

    public void signOutStudent() {
        repo.signOutStudent();
    }

    public void deleteCurrentUser() {
        repo.deleteCurrentUser();
    }

    public String getSignedInUserUID() {
        return repo.getSignedInUserUID();
    }

    //Database

    public void addStudentDetailsToDatabase(Student student) {
        repo.addStudentDetailsToDatabase(student);
    }

    public DatabaseReference getSignedInStudent() {
        return repo.getSignedInStudent();
    }

    public DatabaseReference getSignedInStudentUniversityAffiliations() {
        return repo.getSignedInStudentUniversityAffiliations();
    }

    public Task<Void> addClubToDatabase(Club club) {
        return repo.addClubToDatabase(club);
    }

    public Query getSignedInStudentOwnedClubs() {
        return repo.getSignedInStudentOwnedClubs();
    }

    public Task<Void> addEventToDatabase(Event event) {
        return repo.addEventToDatabase(event);
    }

    public Query getEventsOfClub(String clubId) {
        return repo.getEventsOfClub(clubId);
    }

    public Query searchForClub(String startWith) {
        return repo.searchForClub(startWith);
    }

    /*public void test() {
        //just for testing firebase database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        DatabaseReference x = ref.child("Users").child("TestEntry");
        DatabaseReference y = x.push();
        y.getKey();
    }*/
}
