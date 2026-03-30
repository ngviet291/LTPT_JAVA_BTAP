/*
 * @ (#) CourseMain.java     1.0    3/18/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.presentation;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/18/2026
 * @version:    1.0
 */

import iuh.fit.core.entity.Course;
import iuh.fit.core.service.CourseService;
import iuh.fit.core.service.impl.CourseServiceImpl;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.JacksonDataMapper;
import iuh.fit.infrastructure.persistence.CourseRepositoryImpl;

import java.util.List;

public class CourseMain {
    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "nguyenvantruong";
        String dbName = "truong";

        Neo4jConnManager conn = new Neo4jConnManager(uri,username, password, dbName);
        GenericDataMapper dataMapper = new JacksonDataMapper();

        CourseRepositoryImpl repo = new CourseRepositoryImpl(conn, dataMapper);
        CourseService service = new CourseServiceImpl(repo);

        // test find
        List<Course> ds = service.findCoursesByDepartmentId("CS");
        ds.forEach(System.out::println);

        // test add
        Course course = new Course();
        course.setId("IE202");
        course.setName("Simulation");
        course.setHours(3);

        boolean kq = service.addCourse("IE", course);
        System.out.println("Add thành công: " + kq);
    }
}
