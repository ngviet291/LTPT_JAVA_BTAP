/*
 * @ (#) CourseServiceImpl.java     1.0    3/18/2026
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



import iuh.fit.core.entity.Course;
import iuh.fit.core.service.CourseService;
import iuh.fit.infrastructure.persistence.CourseRepositoryImpl;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseRepositoryImpl courseRepository;

    public CourseServiceImpl(CourseRepositoryImpl courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findCoursesByDepartmentId(String deptId) {
        if(deptId == null || deptId.isEmpty()){
            throw new IllegalArgumentException("deptId không được rỗng");
        }
        return courseRepository.findCourse(deptId);
    }

    @Override
    public boolean addCourse(String deptId, Course course) {
        if(deptId == null || deptId.isEmpty()){
            throw new IllegalArgumentException("deptId không được rỗng");
        }

        if(course == null){
            throw new IllegalArgumentException("course không được null");
        }

        if(course.getId() == null || course.getId().isEmpty()){
            throw new IllegalArgumentException("course_id không được rỗng");
        }

        return courseRepository.addCourse(deptId, course);
    }
}