package com.arnabkundu.employeedemo.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_tbl")
data class Employee(
    val employee_name: String,
    val employee_number: String,
    val employee_salary: String,
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Long = 0
}

