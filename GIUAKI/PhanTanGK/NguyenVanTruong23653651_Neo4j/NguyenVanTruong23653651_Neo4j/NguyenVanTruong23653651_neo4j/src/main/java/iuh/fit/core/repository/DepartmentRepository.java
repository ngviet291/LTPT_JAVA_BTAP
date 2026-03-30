    package iuh.fit.core.repository;

import iuh.fit.core.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentRepository {
    List<Department> getDepartmentsByFulltext(String keyword);
    List<Department> getDepartmentsWithHighestStudents();
    List<String> getDeanNames();
    Map<Department, Long> getNumberOfStudentsOfEachDepartment();
     Map<Department, Long> getNumberOfStudentsOfEachDepartmentDESC();
    List<Department>getListDepartment();
}
