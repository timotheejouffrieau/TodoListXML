package com.example.todolistxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//https://developer.android.com/develop/ui/views/layout/recyclerview

class TaskAdapter(private val taskList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val contentTextView: TextView
        val priorityTextView: TextView

        init {
            titleTextView = view.findViewById(R.id.titleItem)
            contentTextView = view.findViewById(R.id.contentItem)
            priorityTextView = view.findViewById(R.id.priorityItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.titleTextView.text = taskList[position].title
        holder.contentTextView.text = taskList[position].content
        holder.priorityTextView.text = taskList[position].priority.name
    }


}