package com.example.todolistxml

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toPriority(value: Int) = Priority.getPriorityFromValue(value)

    @TypeConverter
    fun fromPriority(priority: Priority) = priority.value

}