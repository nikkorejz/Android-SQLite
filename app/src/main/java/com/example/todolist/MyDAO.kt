package com.example.todolist

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDAO {

    @Query("INSERT INTO deals(title) VALUES (:title);")
    fun add(title: String)

    @Query("SELECT id, title, is_done FROM deals ORDER BY id DESC")
    fun getAll(): List<MyDeal>

    @Query("DELETE FROM deals WHERE id = :id;")
    fun delete(id: Long)

    @Query("UPDATE deals SET is_done = NOT is_done WHERE id = :id;")
    fun toggle(id: Long)
}