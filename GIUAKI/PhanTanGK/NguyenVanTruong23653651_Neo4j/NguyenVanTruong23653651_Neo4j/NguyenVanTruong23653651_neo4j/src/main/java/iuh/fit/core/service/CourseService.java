/*
 * @ (#) CourseService.java     1.0    3/18/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.core.service;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/18/2026
 * @version:    1.0
 */


import iuh.fit.core.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findCoursesByDepartmentId(String deptId);
    boolean addCourse(String deptId, Course course);
}