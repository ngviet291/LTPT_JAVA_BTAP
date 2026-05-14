package service;

import dao.DepartmentDAO;
import entity.Department;

import java.util.List;
import java.util.Map;

public class DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    public Map<Department, Long> getNumberOfStudentsByDepartment (){
        return  departmentDAO.getNumberOfStudentsByDepartment();
    }
    public List<Department> listDepartmentsWithoutStudents(){
        return  departmentDAO.listDepartmentsWithoutStudents();
    }
}
