package com.example.androidroomdatabaserecycleviewlivedataviewmodel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Semester {
@PrimaryKey(autoGenerate = true)
    int id;
    String semesterName;
    String semesterGpa;
    String semesterCredit;

    public Semester(String semesterName, String semesterGpa, String semesterCredit) {
        this.semesterName = semesterName;
        this.semesterGpa = semesterGpa;
        this.semesterCredit = semesterCredit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSemesterGpa() {
        return semesterGpa;
    }

    public void setSemesterGpa(String semesterGpa) {
        this.semesterGpa = semesterGpa;
    }

    public String getSemesterCredit() {
        return semesterCredit;
    }

    public void setSemesterCredit(String semesterCredit) {
        this.semesterCredit = semesterCredit;
    }
}
