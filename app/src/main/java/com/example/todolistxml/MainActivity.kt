package com.example.todolistxml

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main) //Associe l'activité à la vue globale

        taskList.apply {
            for (i in 1..100) add(
                Task(
                    i,
                    "Titre $i",
                    "Contenue $i",
                    Priority.values().random()
                )
            )
        }

        val taskListView = findViewById<RecyclerView>(R.id.listViewTask)

        val taskAdapter = TaskAdapter(taskList)

        taskListView.layoutManager = LinearLayoutManager(this)
        taskListView.adapter = taskAdapter

    }
}