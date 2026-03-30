package com.ngviet291.service;

import com.ngviet291.dao.EmployeeDao;
import com.ngviet291.entity.Employee;

public class EmployeeService {
    public final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    public boolean addEmployee(Employee emp){
        if(emp.getId().isEmpty()){
            throw  new RuntimeException("ID null");
        }
        return employeeDao.addEmployee(emp);
    }
}
