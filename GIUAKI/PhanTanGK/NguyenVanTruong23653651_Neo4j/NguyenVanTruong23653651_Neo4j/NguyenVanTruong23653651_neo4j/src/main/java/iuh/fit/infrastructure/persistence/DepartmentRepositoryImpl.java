package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Department;
import iuh.fit.core.repository.DepartmentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;

import iuh.fit.infrastructure.mapper.JacksonDataMapper;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private Neo4jConnManager connManager;
    private GenericDataMapper mapper;

    public DepartmentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }
    //CREATE FULLTEXT INDEX department_fulltext FOR (n:Department) ON EACH [n.name, n. dean]
    @Override
    public List<Department> getDepartmentsByFulltext(String keyword) {
        String cypher = "CALL db.index.fulltext.queryNodes('department_fulltext', $keyword) YIELD node, score WHERE score >= 0.35 RETURN node, score";
        try(var session = connManager.getSession()) {
            return session.executeRead(tx -> {
                var result = tx.run(cypher, Map.of("keyword", keyword));
                return result.list(r -> {
                    Node node = r.get("node").asNode();
//                    System.out.println(node.asMap());
//                    System.out.println(r.get("sc  ore").asDouble());
                    return mapper.toObject(node.asMap(), Department.class);
                });
            });
        }
    }

    @Override
    public List<Department> getDepartmentsWithHighestStudents() {
        String cypher = "MATCH (s:Student)-[:ENROLLED_IN]->(c:Course)-[:BELONGS_TO]->(d:Department)\n" +
                "WITH d, count(s) AS numOfStudents\n" +
                "WITH max(numOfStudents) AS maxNumOfStudents\n" +
                "MATCH (s:Student)-[:ENROLLED_IN]->(c:Course)-[:BELONGS_TO]->(d:Department)\n" +
                "WITH d, count(s) AS numOfStudents, maxNumOfStudents\n" +
                "WHERE numOfStudents = maxNumOfStudents\n" +
                "RETURN d \n";

        try(var session = connManager.getSession()) {
            return session.executeRead(tx -> {
                var result = tx.run(cypher);
                return result.list(r -> mapper.toObject(r.get("d").asNode().asMap(), Department.class));
            });
        }
    }

    @Override
    public List<String> getDeanNames() {
        String cypher = "MATCH (d:Department) RETURN d.dean AS deanName";
        try(var session = connManager.getSession()) {
            return session.executeRead(tx -> {
                var result = tx.run(cypher);
                return result.list(r -> r.get("deanName").asString());
            });
        }
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsOfEachDepartmentDESC() {
        String cypher = "MATCH (s:Student)-[:ENROLLED_IN]->(c:Course)-[:BELONGS_TO]->(d:Department) RETURN d, count(s) as numOfStudents ORDER BY numOfStudents DESC";
        try(var session = connManager.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher);
                List<Record> records = result.list();
                return  records.stream()
                        .map(record -> {
                            Node node = record.get("d").asNode();
                            Department department = mapper.toObject(node.asMap(), Department.class);
                            Long numOfStudents = record.get("numOfStudents").asLong();
                            return new AbstractMap.SimpleEntry<>(department, numOfStudents);
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (aLong, aLong2) -> aLong, LinkedHashMap::new));
            });
        }
    }

    @Override
    public Map<Department, Long> getNumberOfStudentsOfEachDepartment() {
        Map<Department, Long>  map = new HashMap<>();
        String cypher = "MATCH (s:Student)-[:ENROLLED_IN]->(c:Course)-[:BELONGS_TO]->(d:Department) RETURN d, count(s) as numOfStudents";
        try(var session = connManager.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher);
                List<Record> records = result.list();
                for(Record record : records) {
                    Node node = record.get("d").asNode();
                    map.put(mapper.toObject(node.asMap(), Department.class), record.get("numOfStudents").asLong());
                }
                return map;
            });
        }
    }
    //Muốn liệt kê tất cả khoa (Faculty):
    public List<Department> getListDepartment() {
        String cypher = "MATCH (d:Department) RETURN d";

        try (Session session = connManager.getSession()) {
            return session.executeRead(tx -> {
                var result = tx.run(cypher);
                return result.list(r ->
                        mapper.toObject(
                                r.get("d").asNode().asMap(),
                                Department.class
                        )
                );
            });
        }
    }

}
