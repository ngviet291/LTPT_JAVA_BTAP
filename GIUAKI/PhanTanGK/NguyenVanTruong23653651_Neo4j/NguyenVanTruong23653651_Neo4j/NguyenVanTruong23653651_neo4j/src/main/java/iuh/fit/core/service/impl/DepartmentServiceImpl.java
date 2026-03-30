/*
 * @ (#) DepartmentServiceImpl.java     1.0    3/18/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.core.service.impl;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/18/2026
 * @version:    1.0
 */


import iuh.fit.core.entity.Department;
import iuh.fit.core.service.DepartmentService;
import iuh.fit.infrastructure.persistence.DepartmentRepositoryImpl;

import java.util.List;
import java.util.Map;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepositoryImpl repository;

    public DepartmentServiceImpl(DepartmentRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<Department> searchDepartments(String keyword) {
        if(keyword == null || keyword.isEmpty()){
            throw new IllegalArgumentException("keyword không được rỗng");
        }
        return repository.getDepartmentsByFulltext(keyword);
    }

    @Override
    public List<Department> getDepartmentsWithMostStudents() {
        return repository.getDepartmentsWithHighestStudents();
    }

    @Override
    public List<String> getDeanNames() {
        return repository.getDeanNames();
    }

    @Override
    public Map<Department, Long> getStudentCountByDepartmentDESC() {
        return repository.getNumberOfStudentsOfEachDepartmentDESC();
    }

    @Override
    public Map<Department, Long> getStudentCountByDepartment() {
        return repository.getNumberOfStudentsOfEachDepartment();
    }
}