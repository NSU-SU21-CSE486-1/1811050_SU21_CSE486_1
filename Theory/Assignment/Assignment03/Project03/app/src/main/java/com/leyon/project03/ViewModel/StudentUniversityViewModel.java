package com.leyon.project03.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;
import com.leyon.project03.Repository.StudentUniversityRepository;

import java.util.List;

public class StudentUniversityViewModel extends AndroidViewModel {

    StudentUniversityRepository repository;

    LiveData<List<User>> usersList;

    public StudentUniversityViewModel(Application application) {
        super(application);

        repository = new StudentUniversityRepository(application);

        usersList = repository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return repository.getAllUsers();
    }

    public LiveData<List<UniversityAffiliation>> getAllUniversityAffiliations() {
        return repository.getAllUniversityAffiliations();
    }

    public List<UniversityAffiliation> getUniversityAffiliationByUserId(long userid) {
        return repository.getUniversityAffiliationByUserId(userid);
    }

    public long insertUser(User user) {
        return repository.insertUser(user);
    }

    //since universityaffiliation entity need the user entity id, if user entity was not inserted
    //before it, which happens in threads, program crashes
    //use insertUserAndUniversityAffiliations() instead
    /*public void insertUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        repository.insertUniversityAffiliation(universityAffiliation);
    }*/

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

    public void insertUserAndUniversityAffiliations(User user, UniversityAffiliation[] uniAffiliationArray) {
        repository.insertUserAndUniversityAffiliations(user, uniAffiliationArray);
    }
}
