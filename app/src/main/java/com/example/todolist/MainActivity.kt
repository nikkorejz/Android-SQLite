package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = MyDatabase(this)

//        for (i in 0..100) {
//            db.add("Task #$i")
//        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(db)
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.titleEditText)
        findViewById<Button>(R.id.button).setOnClickListener {
            adapter.add(editText.text.toString())
            editText.text.clear()
        }

    }
}