package com.leyon.uniclubz.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leyon.uniclubz.Entity.Student;
import com.leyon.uniclubz.Entity.UniversityAffiliation;
import com.leyon.uniclubz.Repository.Repository;

public class AppViewModel extends AndroidViewModel {

    Repository repo;

    public AppViewModel(@NonNull Application application) {
        super(application);

        repo = new Repository();
    }

    public boolean signUpStudent(String email, String password) {
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

    public void addUserToDatabase() {
        //just for testing firebase database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //ref.child("Users").setValue("test success");
        DatabaseReference usr = ref.child("Users").push();
        Student s = new Student();
        UniversityAffiliation u = new UniversityAffiliation("a","b","c","d",123);
        s.addUniversityAffiliation(u);
        usr.setValue(s);
    }
}
