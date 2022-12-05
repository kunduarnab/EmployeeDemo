package com.arnabkundu.employeedemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arnabkundu.employeedemo.data.db.dao.EmployeeDao
import com.arnabkundu.employeedemo.data.db.entities.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao
}