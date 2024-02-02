package com.example.importanttodos

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodosDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var testDatabase: TodosDatabase
    private lateinit var testDao: TodosDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        testDatabase = Room.inMemoryDatabaseBuilder(
            context,
            TodosDatabase::class.java
        ).allowMainThreadQueries().build()
        testDao = testDatabase.todosDao
    }

    @After
    fun closeDatabase() {
        testDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun database_returnsSizeOfOneAndCorrectTitle_whenNewTodoInserted() = runTest {
        val newTodo = Todo(
            1L,
            "Buy chocolate chip cookies 🍪",
            false
        )

        testDao.insert(newTodo)
        val todos = testDao.getAll().getOrAwaitValue()
        assertThat(todos.size).isEqualTo(1)
        val insertedTodo = testDao.get(1).getOrAwaitValue()
        assertThat(insertedTodo.title).isEqualTo(newTodo.title)
    }

    @Test
    @Throws(Exception::class)
    fun database_returnsCorrectData_whenTodoUpdated() = runTest {
        val newTodo = Todo(
            1L,
            "Buy peanut butter cookies 🥜🍪",
            false
        )

        testDao.insert(newTodo)
        newTodo.title = "Buy ice cream 🍦"
        newTodo.completed = true
        testDao.update(newTodo)
        val updatedTodo = testDao.get(1).getOrAwaitValue()
        assertThat(updatedTodo.title).isEqualTo("Buy ice cream 🍦")
        assertThat(updatedTodo.completed).isTrue()
    }

    @Test
    @Throws(Exception::class)
    fun database_returnsZero_whenAllTodosDeleted() = runTest {
        val newTodo = Todo(
            1L,
            "Buy chocolate 🍫",
            false
        )

        testDao.insert(newTodo)
        testDao.delete(newTodo)
        val todos = testDao.getAll().getOrAwaitValue()
        assertThat(todos.size).isEqualTo(0)
    }
}
