package com.leyon.project03.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;
import com.leyon.project03.Repository.StudentUniversityRepository;

import java.util.List;

public class StudentUniversityViewModel {

    StudentUniversityRepository repository;

    LiveData<List<User>> usersList;

    public StudentUniversityViewModel(Application application) {

        repository = new StudentUniversityRepository(application);

        usersList = repository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return repository.getAllUsers();
    }

    public LiveData<List<UniversityAffiliation>> getAllUniversityAffiliations() {
        return repository.getAllUniversityAffiliations();
    }

    public LiveData<List<UniversityAffiliation>> getUniversityAffiliationByUserId(long userid) {
        return repository.getUniversityAffiliationByUserId(userid);
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public void insertUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        repository.insertUniversityAffiliation(universityAffiliation);
    }

    public void updateUser(User user) {
        repository.updateUser(user);
    }

    public void updateUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        repository.updateUniversityAffiliation(universityAffiliation);
    }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }

    public void deleteUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        repository.deleteUniversityAffiliation(universityAffiliation);
    }

    public void deleteAllUserData() {
        repository.deleteAllUserData();
    }

    public void deleteAllUniversityAffiliationData() {
        repository.deleteAllUniversityAffiliationData();
    }

    public List<UniversityAffiliation> getUserWithUniversityAffiliations() {
        return repository.getUserWithUniversityAffiliations();
    }
}
