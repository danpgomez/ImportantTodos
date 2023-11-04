package com.example.importanttodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTodoViewModel(todoId: Long, private val dao: TodosDao): ViewModel() {
    val todo = dao.get(todoId)
    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean> get() = _navigateToList

    fun updateTodoItem() {
        viewModelScope.launch {
            todo.value?.let { dao.update(it) }
            _navigateToList.value = true
        }
    }

    fun deleteTodoItem() {
        viewModelScope.launch {
            todo.value?.let { dao.delete(it) }
            _navigateToList.value = true
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}
