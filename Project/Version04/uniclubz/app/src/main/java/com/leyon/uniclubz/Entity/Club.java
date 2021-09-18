package com.leyon.uniclubz.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {

    String id;
    String clubName;
    String clubOwnerUID;
    String clubUniversity;
    List<String> clubMembersUIDList = new ArrayList<>();
    List<String> clubMemberJoinRequestUIDList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubOwnerUID() {
        return clubOwnerUID;
    }

    public void setClubOwnerUID(String clubOwnerUID) {
        this.clubOwnerUID = clubOwnerUID;
    }

    public String getClubUniversity() {
        return clubUniversity;
    }

    public void setClubUniversity(String clubUniversity) {
        this.clubUniversity = clubUniversity;
    }

    public List<String> getClubMembersUIDList() {
        return clubMembersUIDList;
    }

    public void setClubMembersUIDList(List<String> clubMembersUIDList) {
        this.clubMembersUIDList = clubMembersUIDList;
    }

    public List<String> getClubMemberJoinRequestUIDList() {
        return clubMemberJoinRequestUIDList;
    }

    public void setClubMemberJoinRequestUIDList(List<String> clubMemberJoinRequestUIDList) {
        this.clubMemberJoinRequestUIDList = clubMemberJoinRequestUIDList;
    }

    public void addUIDToClubMemberJoinRequestUIDList(String uid) {
        clubMemberJoinRequestUIDList.add(uid);
    }

    public void removeUIDToClubMemberJoinRequestUIDList(String uid) {
        //clubMemberJoinRequestUIDList.remove(uid);
    }
}
