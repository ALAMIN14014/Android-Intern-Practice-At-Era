package com.example.androidroomdatabaserecycleviewlivedataviewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SemesterDao {
    @Insert
    void semesterInsert(Semester semester);

    @Update
    void semesterUpdate(Semester semester);

    @Delete
    void semsterDelete(Semester semester);

    @Query("Select * from Semester order by semesterName")
    LiveData<List<Semester>> getAllSemester();

    @Query("Delete from semester")
    void deleteAll();


}
