package com.arnabkundu.employeedemo.ui.employee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnabkundu.employeedemo.data.db.entities.Employee
import com.arnabkundu.employeedemo.data.repo.EmployeeRepository
import com.arnabkundu.employeedemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _responseState = MutableLiveData<Resource<String>>()
    val responseState = _responseState

    fun insertEmployee(employee: Employee) {
        _responseState.value = Resource.Loading()
        if (employee.employee_name == "") {
            _responseState.value = Resource.Error("Please enter employee name")
            return
        }
        if (employee.employee_number == "") {
            _responseState.value = Resource.Error("Please enter employee number")
            return
        }
        if (employee.employee_salary == "") {
            _responseState.value = Resource.Error("Please enter employee salary")
            return
        }
        viewModelScope.launch {
            try {
                employeeRepository.insertEmployee(employee)
                _responseState.value = Resource.Success("Employee added successfully")
            } catch (e: Exception) {
                e.printStackTrace()
                _responseState.value = Resource.Error("Something went wrong")
            }
        }
    }

    fun getAllEmployees() = employeeRepository.getAllEmployees()

    fun deleteEmployee(uid: Long) = viewModelScope.launch {
        employeeRepository.deleteEmployee(uid)
    }

    fun updateEmployee(employee: Employee) = viewModelScope.launch {
        employeeRepository.updateEmployee(employee)
    }
}