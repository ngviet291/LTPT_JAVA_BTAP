package com.ngviet291.app;

import com.ngviet291.dao.EmployeeDao;
import com.ngviet291.entity.Employee;
import com.ngviet291.service.EmployeeService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Employee employee= Employee.builder()
                .id("1021")
                .firstName("Viet")
                .lastName("Nguyen")
                .gender("Nam")
                .phone("0908424063")
                .salaryCoefficient(32.3f)
                .build();
        EmployeeDao employeeDao = new EmployeeDao();
        EmployeeService employeeService= new EmployeeService(employeeDao);
        employeeService.addEmployee(employee);
    }
}
