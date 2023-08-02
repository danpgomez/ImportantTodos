package com.example.importanttodos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TodosViewModelFactory(private val dao: TodosDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TodosDao::class.java).newInstance(dao)
    }
}
