package com.arnabkundu.employeedemo.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "employee_tbl")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    var uid: Long,
    val employee_name: String,
    val employee_number: String,
    val employee_salary: String,
) : Parcelable

