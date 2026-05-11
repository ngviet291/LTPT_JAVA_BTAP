package com.ngviet291.service;

import com.ngviet291.dao.EmployeeDao;
import com.ngviet291.entity.Employee;
import com.ngviet291.entity.Role;

import java.util.List;
import java.util.Map;

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
    public List<Employee> listManagers(){
        return employeeDao.listManagers();
    }
    public Map<Employee,Double> getTotalIncomeOfEmployees(){
        return employeeDao.getTotalIncomeOf Employees();
    }
    public boolean updateRoleOfInvolvement(String empID, String prjID, Role newRole){
        return employeeDao.updateRoleOfInvolvement(empID,prjID,newRole);
    }
}
