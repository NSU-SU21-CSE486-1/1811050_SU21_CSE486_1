package com.leyon.uniclubz.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.leyon.uniclubz.Entity.Student;
import com.leyon.uniclubz.Repository.Repository;

public class AppViewModel extends AndroidViewModel {

    Repository repo;

    public AppViewModel(@NonNull Application application) {
        super(application);

        repo = new Repository();
    }

    //LiveData
    public LiveData<DataSnapshot> getDatabaseLiveData() {
        return repo.getDatabaseLiveData();
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

    //Database

    public void addStudentDetailsToDatabase(Student student) {
        repo.addStudentDetailsToDatabase(student);
    }

    public DatabaseReference getSignedInStudent() {
        return repo.getSignedInStudent();
    }


    /*public void test() {
        //just for testing firebase database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        DatabaseReference curUsr = ref.child("Users").child(repo.getSignedInUserUID());
        Student s = new Student();
        s.setPhoneNumber(1234567890);

        UniversityAffiliation u1 = new UniversityAffiliation();
        u1.setUniversityName("a");
        u1.setDepartment("b");
        u1.setStudentID(111);
        u1.setStudyLevel("d");
        u1.setUniversityEmail("e");

        UniversityAffiliation u2 = new UniversityAffiliation();
        u2.setUniversityName("a");
        u2.setDepartment("b");
        u2.setStudentID(222);
        u2.setStudyLevel("d");
        u2.setUniversityEmail("e");

        //curUsr.setValue(s);

        DatabaseReference uniAff = curUsr.child("UniversityAffiliations");
        List<UniversityAffiliation> universityAffiliationList = new ArrayList<UniversityAffiliation>();
        universityAffiliationList.add(u1);
        universityAffiliationList.add(u2);
        //uniAff.setValue(universityAffiliationList);

        DatabaseReference users = ref.child("Users");
        //Query res = users.orderByKey().equalTo("Users");
        List<Student> list = new ArrayList<>();
        List<UniversityAffiliation> unilist = new ArrayList<>();

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s: snapshot.getChildren()) {
                    list.add(s.getValue(Student.class));
                }

                //list.get(0).getPhoneNumber();
                //Toast.makeText(getApplication().getApplicationContext(), Integer.toString(list.get(0).getPhoneNumber()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        uniAff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s: snapshot.getChildren()) {
                    unilist.add(s.getValue(UniversityAffiliation.class));
                }

                Toast.makeText(getApplication().getApplicationContext(), Integer.toString(unilist.size()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
