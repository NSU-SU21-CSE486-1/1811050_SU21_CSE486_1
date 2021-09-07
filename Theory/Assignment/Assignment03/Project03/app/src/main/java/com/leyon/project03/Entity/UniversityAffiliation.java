package com.leyon.project03.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "universityaffiliations",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = {"id"},
                childColumns = {"userid"},
                onDelete = ForeignKey.CASCADE //no onUpdate Cascade because using id that cannot be updated
        )
)
public class UniversityAffiliation {

    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "university_name")
    String universityName;

    @ColumnInfo(name = "study_level")
    String studyLevel;

    @ColumnInfo(name = "department")
    String department;

    @ColumnInfo(name = "university_email")
    String universityEmail;

    @ColumnInfo(name = "university_student_id")
    int universityStudentID;

    @ColumnInfo(name = "userid")
    long userID;

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

    public int getUniversityStudentID() {
        return universityStudentID;
    }

    public void setUniversityStudentID(int universityStudentID) {
        this.universityStudentID = universityStudentID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
