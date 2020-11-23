package com.example.androidroomdatabaserecycleviewlivedataviewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    public SemesterDao semesterDao;
    LiveData<List<Semester>> allSemester;

    public Repository(Application application) {

        //database create
        SemesterDatabase db = SemesterDatabase.getDatabase(application);
        semesterDao = db.semesterDao();
        allSemester = semesterDao.getAllSemester();
    }

    public LiveData<List<Semester>> getAllSemester() {
        return allSemester;
    }

    public void InsertSemester(Semester semester) {

        new Inserttask(semesterDao).execute(semester);

    }

    public class Inserttask extends AsyncTask<Semester, Void, Void> {
        private SemesterDao semesterDao;

        public Inserttask(SemesterDao semesterDao) {
            this.semesterDao = semesterDao;
        }

        @Override
        protected Void doInBackground(Semester... semesters) {
            semesterDao.semesterInsert(semesters[0]);
            return null;
        }

    }

}
