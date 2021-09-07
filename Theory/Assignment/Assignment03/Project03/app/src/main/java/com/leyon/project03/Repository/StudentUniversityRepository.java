package com.leyon.project03.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.leyon.project03.DAO.UniversityAffiliationDAO;
import com.leyon.project03.DAO.UserDAO;
import com.leyon.project03.Database.UniversityStudentRoomDatabase;
import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;

import java.util.List;

public class StudentUniversityRepository {

    private UserDAO userDAO;
    private UniversityAffiliationDAO universityAffiliationDAO;

    public StudentUniversityRepository(Application application) {
        UniversityStudentRoomDatabase db = UniversityStudentRoomDatabase.getInstance(application);

        userDAO = db.userDAO();
        universityAffiliationDAO = db.universityAffiliationDAO();
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public LiveData<List<UniversityAffiliation>> getAllUniversityAffiliations() {
        return universityAffiliationDAO.getAllUniversityAffiliations();
    }

    public LiveData<List<UniversityAffiliation>> getUniversityAffiliationByUserId(long userid) {
        return universityAffiliationDAO.getUniversityAffiliationByUserId(userid);
    }

    public void insertUser(User user) {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            userDAO.insert(user);
        });
    }

    public void insertUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            universityAffiliationDAO.insert(universityAffiliation);
        });
    }

    public void updateUser(User user) {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            userDAO.update(user);
        });
    }

    public void updateUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            universityAffiliationDAO.update(universityAffiliation);
        });
    }

    public void deleteUser(User user) {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            userDAO.deleteUser(user);
        });
    }

    public void deleteUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            universityAffiliationDAO.deleteUniversityAffiliation(universityAffiliation);
        });
    }

    public void deleteAllUserData() {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            userDAO.deleteAll();
        });
    }

    public void deleteAllUniversityAffiliationData() {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            universityAffiliationDAO.deleteAll();
        });
    }

    public List<UniversityAffiliation> getUserWithUniversityAffiliations() {
        return universityAffiliationDAO.getUserWithUniversityAffiliations();
    }
}
