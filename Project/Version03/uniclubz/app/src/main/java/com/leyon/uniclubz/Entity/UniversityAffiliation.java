package com.leyon.uniclubz.Entity;

import java.io.Serializable;

public class UniversityAffiliation implements Serializable {

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUniversityEmail() {
        return universityEmail;
    }

    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
