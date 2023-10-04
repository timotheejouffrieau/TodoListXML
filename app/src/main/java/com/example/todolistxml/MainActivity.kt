package com.example.todolistxml

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main) //Associe l'activité à la vue globale

        val list = mutableListOf<String>().apply { for (i in 1..100) add("$i") }

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        val taskListView = findViewById<ListView>(R.id.listViewTask)
        taskListView.adapter = arrayAdapter

    }
}