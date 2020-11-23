package com.example.myroomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    //insert query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    //delete query
    @Delete
    void  delete(MainData mainData);
    //Delete all query
    @Delete
    void reset(List<MainData>mainData);

    //update query
    @Query("UPDATE table_name SET text = :sTEXT WHERE ID = :sID")
    void update(int sID, String sTEXT);

    //get all query
    @Query("SELECT * FROM TABLE_NAME")
    List<MainData>getall();


}
