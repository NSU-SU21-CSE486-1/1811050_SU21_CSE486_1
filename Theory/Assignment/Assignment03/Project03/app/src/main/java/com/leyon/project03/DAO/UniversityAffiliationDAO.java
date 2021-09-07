package com.leyon.project03.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;

import java.util.List;

@Dao
public interface UniversityAffiliationDAO {

    @Query("SELECT * FROM universityaffiliations ORDER BY id ASC")
    LiveData<List<UniversityAffiliation>> getAllUniversityAffiliations();

    @Query("SELECT * FROM universityaffiliations WHERE userid = :userid")
    LiveData<List<UniversityAffiliation>> getUniversityAffiliationByUserId(long userid);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UniversityAffiliation universityAffiliation);

    @Update
    void update(UniversityAffiliation universityAffiliation);

    @Delete
    void deleteUniversityAffiliation(UniversityAffiliation universityAffiliation);

    @Query("DELETE from universityaffiliations")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM universityaffiliations")
    List<UniversityAffiliation> getUserWithUniversityAffiliations();
}
