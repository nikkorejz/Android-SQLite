package com.example.todolist

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MyAdapter() : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    companion object {
        const val TAG = "MyAdapter"
    }

    private var list: MutableList<MyDeal> = MyApp.db.myDao().getAll().toMutableList()

    fun getData() {

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView
        var checkBox: CheckBox
        private var deleteBtn: ImageButton
        var id: Long = -1

        init {
            textView = view.findViewById(R.id.item_text)
            checkBox = view.findViewById(R.id.item_checkbox)
//            checkBox.setOnCheckedChangeListener { button, isChecked ->
//                toggle(id)
//            }
            checkBox.setOnClickListener {
                if (id >= 0) {
                    toggle(id)
                }
            }

            deleteBtn = view.findViewById(R.id.item_delete_button)
            deleteBtn.setOnClickListener {
                if (id >= 0) {
                    delete(id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.my_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun toggle(id: Long) {
        MyApp.db.myDao().toggle(id)
        val position = list.indexOfFirst { it.id == id }
        list = list.map {
            if (it.id == id) {
                Log.i(TAG, "Changed $id")
                return@map MyDeal(
                    it.id,
                    it.title,
                    !it.is_done
                )
            }
            it
        }.toMutableList()
//        list = db.getAll().toMutableList()
        notifyItemChanged(position)
        Log.i(TAG, "Toggle on position $position")
    }

    fun add(title: String) {
        val id = MyApp.db.myDao().add(title)
        list = MyApp.db.myDao().getAll().toMutableList()
//        list.add(0, MyDeal(id, title))
        notifyItemInserted(0)
    }

    fun delete(id: Long) {
        MyApp.db.myDao().delete(id)
        val position = list.indexOfFirst { it.id == id }
        list = list.filter { it.id != id }.toMutableList()
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        holder.id = list[position].id
        holder.textView.text = list[position].title
        val isDone = list[position].is_done
        holder.checkBox.isChecked = isDone
        if (isDone) {
            holder.textView.paintFlags =
                Paint.STRIKE_THRU_TEXT_FLAG or holder.textView.paintFlags
            holder.textView.setTextColor(
                ContextCompat.getColor(
                    holder.textView.context,
                    R.color.teal_200
                )
            )
        }
    }

}