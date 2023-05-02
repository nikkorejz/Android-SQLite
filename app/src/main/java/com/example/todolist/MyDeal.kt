package com.example.todolist

data class MyDeal(
    val id: Long,
    val title: String,
    val is_done: Boolean = false
)