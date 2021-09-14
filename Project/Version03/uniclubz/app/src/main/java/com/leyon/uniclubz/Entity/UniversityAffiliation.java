package com.leyon.uniclubz.Entity;

public class UniversityAffiliation {

    long id;

    String universityName;

    String studyLevel;

    String department;

    String universityEmail;

    int studentID;

    public UniversityAffiliation() {
        //empty constructor
    }

    public UniversityAffiliation(String universityName, String studyLevel, String department, String universityEmail, int studentID) {
        this.universityName = universityName;
        this.studyLevel = studyLevel;
        this.department = department;
        this.universityEmail = universityEmail;
        this.studentID = studentID;
    }
}
