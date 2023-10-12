package com.example.todolistxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//https://developer.android.com/develop/ui/views/layout/recyclerview

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList: List<Task> = listOf()
    var onItemClick: ((Task) -> Unit) = {}

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

    fun submitList(list: List<Task>) {
        taskList = taskList.toMutableList().apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        with(taskList[position]) {
            holder.titleTextView.text = title
            holder.contentTextView.text = content
            holder.priorityTextView.text = priority.name
            holder.itemView.setOnClickListener { onItemClick(this) }
        }
    }


}