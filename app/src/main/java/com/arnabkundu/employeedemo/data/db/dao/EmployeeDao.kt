package com.arnabkundu.employeedemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arnabkundu.employeedemo.data.db.entities.Employee

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(user: Employee)

    @Update
    suspend fun update(user: Employee)

    @Query("SELECT * FROM employee_tbl")
    fun getAllEmployee(): LiveData<List<Employee>>

    @Query("DELETE FROM employee_tbl WHERE uid = :uid")
    suspend fun deleteEmployee(uid: Long)
}