package com.leyon.project02;

public class UniversityData {

    private String universityName;
    private String department;
    private String studyLevel;
    private int studentID;
    private String universityEmail;

    public UniversityData(String universityName, String department, String studyLevel, int studentID, String universityEmail) {
        this.universityName = universityName;
        this.department = department;
        this.studyLevel = studyLevel;
        this.studentID = studentID;
        this.universityEmail = universityEmail;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getUniversityEmail() {
        return universityEmail;
    }

    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }
}
