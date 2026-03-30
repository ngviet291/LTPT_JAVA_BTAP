package iuh.fit;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.core.entity.Student;

import java.util.Map;

public class JacksonDemo2 {
    public static void main(String[] args) {

        ObjectMapper  mapper = new ObjectMapper();

        Map<String, Object> map = Map.of("student_id", "23222221",
                "name", "Binh An",
                "gpa", 3.2);

//        DTO -> Map<String, Object> -> Entity
//        Entity -> Map<String, Object> -> DTO

//      Jackson
        Student student = mapper.convertValue(map, Student.class);

        System.out.println(student);
    }
}
