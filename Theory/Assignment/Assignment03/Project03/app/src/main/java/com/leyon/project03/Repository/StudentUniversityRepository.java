package com.leyon.project03.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.leyon.project03.DAO.UniversityAffiliationDAO;
import com.leyon.project03.DAO.UserDAO;
import com.leyon.project03.Database.UniversityStudentRoomDatabase;
import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public List<UniversityAffiliation> getUniversityAffiliationByUserId(long userid) {
        return universityAffiliationDAO.getUniversityAffiliationByUserId(userid);
    }

    public long  insertUser(User user) {
        final long[] id = new long[1];
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(new Runnable() {
            @Override
            public void run() {
                id[0] = userDAO.insert(user);
            }
        });

        return id[0];
    }

    //don't use. have issue with thread. use insertUserAndUniversityAffiliations() instead
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

    public void insertUserAndUniversityAffiliations(User user, UniversityAffiliation[] uniAffiliationArray) {
        UniversityStudentRoomDatabase.getDatabaseWriteExecutor().execute(()->{
            long userIDInDatabase = userDAO.insert(user);

            for (UniversityAffiliation ua : uniAffiliationArray) {
                ua.setUserID(userIDInDatabase);
                universityAffiliationDAO.insert(ua);
            }
        });
    }
}
