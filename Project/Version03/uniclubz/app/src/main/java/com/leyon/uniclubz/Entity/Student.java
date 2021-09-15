package com.leyon.uniclubz.Entity;

import java.util.ArrayList;
import java.util.List;

public class Student {

    String id;

    String name;

    String dateOfBirth;

    int nid;

    String bloodGroup;

    String personalEmail;

    int phoneNumber;

    List<UniversityAffiliation> universityAffiliationList = new ArrayList<UniversityAffiliation>();

    public Student() {
        //empty constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.bloodGroup = bloodGroup.toLowerCase();
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<UniversityAffiliation> getUniversityAffiliationList() {
        return universityAffiliationList;
    }

    public void setUniversityAffiliationList(List<UniversityAffiliation> universityAffiliationList) {
        this.universityAffiliationList = universityAffiliationList;
    }

    public void addUniversityAffiliation(UniversityAffiliation universityAffiliation) {
        universityAffiliationList.add(universityAffiliation);
    }
}
