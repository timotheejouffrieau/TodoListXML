package com.example.todolistxml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    private var isPrioritySorted = false


    private val _taskList = MutableLiveData<List<Task>>(mutableListOf()) //Attribut que l'on va modifier
    val taskList: LiveData<List<Task>> = _taskList  //Attribut qui sera visible pour les autres classes


    fun insertTask(newTask: Task?) {
        if (newTask != null) {
            val list = _taskList.value!!.toMutableList()
            list.find { task -> task.id == newTask.id }//On cherche un élément qui existe déjà
                ?.apply {
                    id = newTask.id
                    title = newTask.title
                    content = newTask.content
                    priority = newTask.priority
                } ?: list.add(newTask) //Si on ne trouve pas d'élément, on en ajoute un nouveau

            _taskList.value = list
        }
    }

    fun sortList() {
        // sortby{} modifie la liste d'origine
        // sortedby{} renvoi une copie modifié sans modifié celle d'origine
        if (!isPrioritySorted) {
            _taskList.value = _taskList.value?.sortedBy { it.priority.value }
        } else {
            _taskList.value = _taskList.value?.sortedBy { it.id }
        }
        isPrioritySorted = !isPrioritySorted
    }

}