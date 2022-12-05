package com.arnabkundu.employeedemo.util

import com.arnabkundu.employeedemo.data.db.entities.Employee

interface ListListener {
    fun onDelete(employee: Employee)
    fun onEdit(employee: Employee)
}