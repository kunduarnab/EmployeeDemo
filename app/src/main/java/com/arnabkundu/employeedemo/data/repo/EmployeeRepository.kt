package com.arnabkundu.employeedemo.data.repo

import com.arnabkundu.employeedemo.data.db.AppDatabase
import com.arnabkundu.employeedemo.data.db.entities.Employee
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {

    suspend fun insertEmployee(employee: Employee) =
        appDatabase.getEmployeeDao().insert(employee)

    suspend fun updateEmployee(employee: Employee, uid: Long) =
        appDatabase.getEmployeeDao().updateEmployee(
            employee.employee_name,
            employee.employee_number,
            employee.employee_salary,
            uid,
        )

    fun getAllEmployees() =
        appDatabase.getEmployeeDao().getAllEmployees()

    suspend fun deleteEmployee(uid: Long) =
        appDatabase.getEmployeeDao().deleteEmployee(uid)

}