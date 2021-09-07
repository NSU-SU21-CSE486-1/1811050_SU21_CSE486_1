package com.leyon.project03.DAO.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.leyon.project03.Entity.UniversityAffiliation;
import com.leyon.project03.Entity.User;

import java.util.List;

public class UserWithUniversityAffiliations {

    @Embedded
    User user;
    @Relation(parentColumn = "id", entityColumn = "userID") //id in user, and userID in universityAffiliation
    UniversityAffiliation universityAffiliation;

    private List<UniversityAffiliation> universityAffiliations;

    public List<UniversityAffiliation> UserWithUniversityAffiliations() {
        return universityAffiliations;
    }
}
