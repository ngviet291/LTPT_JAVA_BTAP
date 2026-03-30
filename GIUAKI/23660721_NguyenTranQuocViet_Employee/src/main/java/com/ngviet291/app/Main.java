package com.ngviet291.app;

import com.ngviet291.dao.EmployeeDao;
import com.ngviet291.entity.Employee;
import com.ngviet291.entity.Role;
import com.ngviet291.mapper.DataMapper;
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
        DataMapper dataMapper= new DataMapper();
        EmployeeDao employeeDao = new EmployeeDao(dataMapper);
        EmployeeService employeeService= new EmployeeService(employeeDao);
//        employeeService.addEmployee(employee);
//        employeeService.listManagers().forEach(System.out::println);
//        employeeService.getTotalIncomeOfEmployees().forEach((k,v)-> System.out.println(k+":"+v));
        if(employeeService.updateRoleOfInvolvement("E23","P08", Role.RESPONSIBLE)){
            System.out.println("Cap nhat thanh cong");
        }else System.out.println("Cap nhat that bai");
    }
}
