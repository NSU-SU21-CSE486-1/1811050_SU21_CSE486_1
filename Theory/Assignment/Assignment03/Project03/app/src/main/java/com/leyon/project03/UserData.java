package com.leyon.project03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserData implements Serializable {

    String userName;
    String dateOfBirth;
    int nid;
    String bloodGroup;

    List<UniversityData> universityAffiliations = new ArrayList<UniversityData>();

    String personalEmail;

    public UserData() {
        //empty constructor
    }

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
        return (ArrayList<UniversityData>) universityAffiliations;
    }

    public void addUniversityAffiliation(String universityName, String department, String studyLevel, int studentID, String universityEmail) {
        universityAffiliations.add(new UniversityData(universityName, department, studyLevel, studentID, universityEmail));
    }

    public void addUniversityAffiliation(UniversityData newUniversityAffiliation) {
        universityAffiliations.add(newUniversityAffiliation);
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
