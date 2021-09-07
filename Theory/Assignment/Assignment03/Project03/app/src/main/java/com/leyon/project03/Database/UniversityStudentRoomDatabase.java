package com.leyon.project03.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.leyon.project03.DAO.UniversityAffiliationDAO;
import com.leyon.project03.DAO.UserDAO;
import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, UniversityAffiliation.class}, version = 1)
public abstract class UniversityStudentRoomDatabase extends RoomDatabase {

    private static UniversityStudentRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract UserDAO userDAO();
    public abstract UniversityAffiliationDAO universityAffiliationDAO();

    public static ExecutorService getDatabaseWriteExecutor(){
        return databaseWriteExecutor;
    }

    public static synchronized UniversityStudentRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            // Create database here
            INSTANCE = Room.databaseBuilder(
                    context,
                    UniversityStudentRoomDatabase.class,
                    "studentuniversitydatabase"
                ).fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
            }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
            @Override
            public void onOpen (@NonNull SupportSQLiteDatabase db){
                super.onOpen(db);
            }
        };

}
