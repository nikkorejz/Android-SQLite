package com.example.todolist

import android.app.Application
import androidx.room.Room

class MyApp : Application() {

    companion object {
        lateinit var db: MyRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            MyRoomDatabase::class.java,
            "MyDatabase.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}