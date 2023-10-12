package com.example.todolistxml

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task ORDER BY id")
    fun getAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    //Exemple pour l'utilisation d'un paramètre
    @Query("SELECT * FROM Task WHERE priority LIKE :priority")
    fun getAllForPriority(priority: Priority): List<Task>

    //Exemple pour la suppression
    @Delete
    fun delete(task: Task)
}