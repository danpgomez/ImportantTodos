package com.example.importanttodos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodosViewModel(val dao: TodosDao): ViewModel() {
    var newTodoTitle = ""
    val todos = dao.getAll().map { formatTodos(it) }

    fun addTodo() {
        viewModelScope.launch {
            val todo = Todo()
            todo.title = newTodoTitle
            dao.insert(todo)
        }
    }

    private fun formatTodo(todo: Todo): String {
        return """
            Title: ${todo.title}
            Completed: ${todo.completed}
            ID: ${todo.id}
        """.trimIndent()
    }

    private fun formatTodos(todosList: List<Todo>): String {
        return todosList.fold("") { accumulator, todo ->
            accumulator + '\n' + formatTodo(todo)
        }
    }
}
