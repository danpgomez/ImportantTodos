package com.example.importanttodos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodosDao {

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todos_table WHERE id = :todoId")
    fun get(todoId: Long): LiveData<Todo>

    @Query("SELECT * FROM todos_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Todo>>
}
