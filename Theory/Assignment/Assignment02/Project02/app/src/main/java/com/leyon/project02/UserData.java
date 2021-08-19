package com.leyon.project02;

import java.util.ArrayList;

public class UserData {

    String userName;
    String dateOfBirth;
    int nid;
    String bloodGroup;

    ArrayList<UniversityData> universityAffiliations;

    String personalEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public ArrayList<UniversityData> getUniversityAffiliations() {
        return universityAffiliations;
    }

    public void addUniversityAffiliation(String universityName, String department, String studyLevel, int studentID, String universityEmail) {
        universityAffiliations.add(new UniversityData(universityName, department, studyLevel, studentID, universityEmail));
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    int phoneNo;
}
