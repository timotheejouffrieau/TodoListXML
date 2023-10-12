package com.example.todolistxml

enum class Priority(val value: Int) {
    HIGH(0),
    MEDIUM(1),
    LOW(2);

    companion object{
        fun getPriorityFromValue(value: Int) = run { values().find { it.value == value } } ?: LOW
    }
}
