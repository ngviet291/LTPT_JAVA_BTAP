/*
 * @ (#) CourseRepositoryImpl.java     1.0    3/18/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.infrastructure.persistence;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/18/2026
 * @version:    1.0
 */

import iuh.fit.core.entity.Course;
import iuh.fit.core.entity.Department;
import iuh.fit.core.repository.DepartmentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.JacksonDataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;

public class CourseRepositoryImpl {
    private Neo4jConnManager con  ;
    private GenericDataMapper mapper;

    public CourseRepositoryImpl(Neo4jConnManager con, GenericDataMapper mapper) {
        this.con = con;
        this.mapper = mapper;
    }
    //“Tìm danh sách các môn học thuộc một khoa nhất định khi biết mã khoa.”
    public List<Course> findCourse(String id){
        String cypher ="MATCH (c:Course)-[:BELONGS_TO]->(d:Department {dept_id:$dept_id}) RETURN c";
        Map<String,Object> map = Map.of("dept_id",id);

        try(Session session = con.getSession()){
            return session.executeRead(tx->{
                Result result = tx.run(cypher,map);
                return result.list(r->{
                    Node node = r.get("c").asNode(); // đúng key
                    return mapper.toObject(node.asMap(),Course.class);
                });
            });
        }
    }
    public boolean addCourse(String dept_id, Course course){
        String query = """
        MATCH (d:Department {dept_id: $dept_id})
        CREATE (c:Course {
            course_id: $course_id,
            name: $name,
            hours: $hours
        })-[:BELONGS_TO]->(d)
    """;

        Map<String,Object> map = mapper.toMap(course);
        map.put("dept_id", dept_id);

        try(Session session = con.getSession()){
            return session.executeWrite(tx -> {
                Result result = tx.run(query, map);
                return result.consume().counters().nodesCreated() > 0;
            });
        }
    }

    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "nguyenvantruong";
        String dbName = "truong";
        Neo4jConnManager conn = new Neo4jConnManager(uri,username, password, dbName);
        GenericDataMapper dataMapper = new JacksonDataMapper();
        CourseRepositoryImpl courseRepository = new CourseRepositoryImpl(conn, dataMapper);
        List<Course> ds = courseRepository.findCourse("CS");
        ds.forEach(v-> System.out.println(v));

    }
}
