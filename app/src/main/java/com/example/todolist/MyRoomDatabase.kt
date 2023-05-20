package com.example.todolist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MyDeal::class), version = 1)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun myDao(): MyDAO
}