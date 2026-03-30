package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Student;
import iuh.fit.core.repository.StudentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.JacksonDataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {
    private Neo4jConnManager connManager;
    private GenericDataMapper mapper;
    public StudentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }

    @Override
    public Student findStudentById(String studentId) {
        String cypher = "MATCH (n:Student{student_id:$student_id}) RETURN n ";
        Map<String, Object> params = Map.of("student_id", studentId);

        try(Session session = connManager.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);
                if(result.hasNext()){
                    Node node = result.single().get("n").asNode();

                    return mapper.toObject(node.asMap(), Student.class);
                }
                return null;
            });
        }
    }

    @Override
    public boolean createStudent(Student student) {
        String cypher = "CREATE (c:Student{student_id:$student_id, name:$name, gpa:$gpa}) ";
        Map<String, Object> paras = mapper.toMap(student);

        try(Session session = connManager.getSession()){
            return session.executeWrite(tx -> {
                Result result = tx.run(cypher, paras);
                return result.consume().counters().nodesCreated() > 0;
            });
        }
    }

    @Override
    public boolean updateStudent(String student,String name) {
        String cypher ="MATCH(s:Student{student_id:$student_id})SET s.name=$name";
        Map<String,Object> map= Map.of("student_id",student,"name",name);


        try(Session session = connManager.getSession()){
            return session.executeWrite(tx->{
                Result result = tx.run(cypher,map);
                return result.consume().counters().propertiesSet()>0;
            });
        }
        //return false;
    }
    public boolean updateStudent1(String id,Student student){
        String cypher ="MATCH(s:Student{student_id:$student_id})" +
                "SET s.name=$name,s.gpa=$gpa";
        Map<String,Object>map =Map.of(
                "student_id",id,
                "name",student.getName(),
                "gpa",student.getGpa()

        );
        try(Session session = connManager.getSession()){
            return session.executeWrite(tx->{
                Result result = tx.run(cypher,map);
                return result.consume().counters().propertiesSet()>0;
            });
        }
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return false;
    }

    @Override
    public List<Student> getStudents(int page, int size) {
        return List.of();
    }

    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String pw = "nguyenvantruong";
        String dbName = "truong";
        Neo4jConnManager conn = new Neo4jConnManager(uri, username, pw, dbName);

        GenericDataMapper dataMapper = new JacksonDataMapper();

        StudentRepository studentRepository = new StudentRepositoryImpl(conn, dataMapper);
        Student student = studentRepository.findStudentById("22");
        System.out.println(student);

        Student st = Student.builder()
                .id("234444441")
                .name("Anna Tran")
                .gpa(3.0)
                .build();
        if(studentRepository.updateStudent1("11",st)){
            System.out.println("Cập nhật thành công !");
        }
//
//        boolean result = studentRepository.createStudent(st);
//        System.out.println(result);
    }
}
