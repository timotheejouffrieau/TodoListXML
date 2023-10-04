package com.example.todolistxml

data class Task(
    val id: Int,
    val title: String,
    val content: String = "",
    val priority: Priority
)
