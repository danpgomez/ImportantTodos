package com.example.importanttodos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var title: String = "",
    var completed: Boolean = false,
)
