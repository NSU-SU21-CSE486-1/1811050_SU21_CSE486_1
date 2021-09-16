package com.leyon.uniclubz.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable {

    long id;
    String eventName;
    boolean isPublic;
    int eventOrganizingClubId;
    String eventDate;
    String eventTime;
    List<String> eventJoiningMembersUID = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getEventOrganizingClubId() {
        return eventOrganizingClubId;
    }

    public void setEventOrganizingClubId(int eventOrganizingClubId) {
        this.eventOrganizingClubId = eventOrganizingClubId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public List<String> getEventJoiningMembersUID() {
        return eventJoiningMembersUID;
    }

    public void setEventJoiningMembersUID(List<String> eventJoiningMembersUID) {
        this.eventJoiningMembersUID = eventJoiningMembersUID;
    }

    public void addNewEventJoiningMemberUID(String uid) {
        eventJoiningMembersUID.add(uid);
    }

    public void removeEventJoiningMemberUID(String uid) {
        //eventJoiningMembersUID.remove(uid);
    }
}
