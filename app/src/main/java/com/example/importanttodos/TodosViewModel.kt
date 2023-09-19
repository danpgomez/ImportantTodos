package com.example.importanttodos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodosViewModel(val dao: TodosDao): ViewModel() {
    var newTodoTitle = ""
    val todos = dao.getAll()

    fun addTodo() {
        viewModelScope.launch {
            val todo = Todo()
            todo.title = newTodoTitle
            dao.insert(todo)
        }
    }
}
