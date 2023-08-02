package com.example.importanttodos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodosDatabase: RoomDatabase() {
    abstract val todosDao: TodosDao

    companion object {

        @Volatile
        private var INSTANCE: TodosDatabase? = null

        fun getInstance(context: Context): TodosDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodosDatabase::class.java,
                        "todo_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
