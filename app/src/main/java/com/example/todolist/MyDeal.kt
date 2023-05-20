package com.example.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "deals")
data class MyDeal(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo
    val title: String,
    @ColumnInfo(defaultValue = "0")
    val is_done: Boolean = false
)
