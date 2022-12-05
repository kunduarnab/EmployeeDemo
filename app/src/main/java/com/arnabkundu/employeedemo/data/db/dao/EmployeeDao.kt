package com.arnabkundu.employeedemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arnabkundu.employeedemo.data.db.entities.Employee

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(employee: Employee)

    @Query("SELECT * FROM employee_tbl")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Query("DELETE FROM employee_tbl WHERE uid = :uid")
    suspend fun deleteEmployee(uid: Long)

    @Query("UPDATE employee_tbl SET employee_name=:name AND employee_number=:number AND employee_salary=:salary WHERE uid=:uid")
    suspend fun updateEmployee(name: String, number: String, salary: String, uid: Long)
}