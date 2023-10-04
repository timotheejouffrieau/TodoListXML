package com.example.todolistxml

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

class EditTaskActivity : AppCompatActivity() {

    private val TAG = "EditTaskActivity"

    private lateinit var currentTask: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        currentTask = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(TASK, Task::class.java)
        } else {
            intent.getParcelableExtra(TASK)
        } ?: Task(1)


        val titleEditText = findViewById<EditText>(R.id.titleTaskEditText)
        val contentEditText = findViewById<EditText>(R.id.contentTaskEditText)
        val prioritySpinner = findViewById<Spinner>(R.id.prioritySpinner)

        titleEditText.setText(currentTask.title)
        titleEditText.doOnTextChanged { text, _, _, _ ->
            Log.d(TAG, "titleEditText text change to $text")
            currentTask = currentTask.copy(title = text.toString())
        }

        contentEditText.setText(currentTask.content)
        contentEditText.doOnTextChanged { text, _, _, _ ->
            Log.d(TAG, "contentEditText text change to $text")
            currentTask = currentTask.copy(content = text.toString())
        }

        val listPriority = Priority.values()
        prioritySpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listPriority.map { it.name } //transforme la liste de priorité en list de String
        )
        prioritySpinner.setSelection(listPriority.indexOf(currentTask.priority))

        prioritySpinner.onItemSelectedListener = object :
            OnItemSelectedListener { //définition d'un classe anonyme -> https://kotlinlang.org/docs/object-declarations.html
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d(TAG, "prioritySpinner selection change to ${listPriority[position].name}")
                currentTask = currentTask.copy(priority = listPriority[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Override obligatoire
                //Mais on ne fait rien
            }

        }

    }

    companion object {
        const val TASK = "TASK"
    }
}