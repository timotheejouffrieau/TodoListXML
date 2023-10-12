package com.example.todolistxml

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"


    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(AppDatabase.getDatabase(applicationContext).taskDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main) //Associe l'activité à la vue globale

        val taskListView = findViewById<RecyclerView>(R.id.listViewTask)

        val taskAdapter = TaskAdapter()

        taskListView.layoutManager = LinearLayoutManager(this)
        taskListView.adapter = taskAdapter

        val addTaskButton = findViewById<FloatingActionButton>(R.id.addTaskButton)

        val getResultTask =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val newTask = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        it.data?.getParcelableExtra(TASK_RETURN, Task::class.java)
                    } else {
                        it.data?.getParcelableExtra(TASK_RETURN)
                    }
                    viewModel.insertTask(newTask)
                }
            }

        addTaskButton.setOnClickListener {
            val intent = Intent(this, EditTaskActivity::class.java)
            intent.putExtra(
                EditTaskActivity.TASK,
                Task((viewModel.taskList.value?.maxOfOrNull { it.id })?.plus(1) ?: 0)
            )
            getResultTask.launch(intent)
        }


        taskAdapter.onItemClick = {
            val intent = Intent(this, EditTaskActivity::class.java)
            intent.putExtra(EditTaskActivity.TASK, it)
            getResultTask.launch(intent)
        }

        val sortListTaskButton = findViewById<FloatingActionButton>(R.id.sortList)

        sortListTaskButton.setOnClickListener {
            viewModel.changeOrder()
            taskAdapter.submitList(viewModel.sortList(viewModel.taskList.value))
        }

        viewModel.taskList.observe(this) {
            taskAdapter.submitList(viewModel.sortList(it))
        }

    }

    companion object {
        const val TASK_RETURN = "TASK_RETURN"
    }
}