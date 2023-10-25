package com.example.importanttodos

import androidx.recyclerview.widget.DiffUtil

class TodoItemCallback: DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem
}
