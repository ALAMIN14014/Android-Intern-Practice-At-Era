package com.example.androidroomdatabaserecycleviewlivedataviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SemesterViewModel extends AndroidViewModel {
    public Repository repository;
    LiveData<List<Semester>> allSemester;

    public SemesterViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        this.allSemester = repository.getAllSemester();

    }

    public LiveData<List<Semester>> getAllSemester() {
        return allSemester;
    }
    public  void  InsertSemester(Semester semester){

        repository.InsertSemester(semester);
    }
}
