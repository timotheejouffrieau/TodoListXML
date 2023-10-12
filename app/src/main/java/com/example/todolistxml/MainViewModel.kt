package com.example.todolistxml

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val taskDao: TaskDao) : ViewModel() {

    private var isPrioritySorted = false

    val taskList: LiveData<List<Task>> = taskDao.getAll()


    fun insertTask(newTask: Task?) {
        if (newTask != null) {
            viewModelScope.launch {
                taskDao.insert(newTask)
            }
        }
    }

    fun changeOrder(){
        isPrioritySorted = !isPrioritySorted
    }

    fun sortList(list: List<Task>?) : List<Task> {
        return list?.sortedBy { if (isPrioritySorted) it.priority.value else it.id } ?: listOf()
    }

}