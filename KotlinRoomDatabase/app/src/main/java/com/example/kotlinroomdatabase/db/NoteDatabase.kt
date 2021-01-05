package com.example.kotlinroomdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)

abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNotdao(): NoteDao

    companion object {
        //Volatitle means instance is available for other thread
        @Volatile
        private var instance: NoteDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also {
                instance = it
            }


        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "noteDatabase"
        ).build()
    }

}