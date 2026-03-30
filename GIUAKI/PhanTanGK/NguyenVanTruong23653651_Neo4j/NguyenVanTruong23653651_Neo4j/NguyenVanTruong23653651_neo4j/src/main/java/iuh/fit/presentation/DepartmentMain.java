/*
 * @ (#) DepartmentMain.java     1.0    3/18/2026
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

import iuh.fit.core.service.DepartmentService;
import iuh.fit.core.service.impl.DepartmentServiceImpl;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.JacksonDataMapper;
import iuh.fit.infrastructure.persistence.DepartmentRepositoryImpl;

public class DepartmentMain {
    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "nguyenvantruong";
        String dbName = "truong";

        Neo4jConnManager conn = new Neo4jConnManager(uri, username, password, dbName);
        GenericDataMapper mapper = new JacksonDataMapper();

        DepartmentRepositoryImpl repo = new DepartmentRepositoryImpl(conn, mapper);
        DepartmentService service = new DepartmentServiceImpl(repo);
            //
        System.out.println(repo.getListDepartment());
//
//        // test search
//        service.searchDepartments("CS").forEach(System.out::println);
//
//        // test dean
//        service.getDeanNames().forEach(System.out::println);
//
//        // test count DESC
//        service.getStudentCountByDepartmentDESC()
//                .forEach((d, count) -> System.out.println(d + " -> " + count));


}}
