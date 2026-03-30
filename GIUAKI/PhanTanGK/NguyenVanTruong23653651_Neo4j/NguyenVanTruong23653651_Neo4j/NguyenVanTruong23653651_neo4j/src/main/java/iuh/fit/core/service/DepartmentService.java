/*
 * @ (#) DepartmentService.java     1.0    3/18/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.core.service;

import iuh.fit.core.entity.Department;

import java.util.List;
import java.util.Map;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/18/2026
 * @version:    1.0
 */
public interface DepartmentService {
    List<Department> searchDepartments(String keyword);
    List<Department> getDepartmentsWithMostStudents();
    List<String> getDeanNames();
    Map<Department, Long> getStudentCountByDepartmentDESC();
    Map<Department, Long> getStudentCountByDepartment();
}
