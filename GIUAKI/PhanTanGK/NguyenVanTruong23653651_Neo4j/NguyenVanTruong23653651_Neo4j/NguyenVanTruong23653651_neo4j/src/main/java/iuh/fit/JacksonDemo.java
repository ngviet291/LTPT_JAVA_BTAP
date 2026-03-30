package iuh.fit;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.core.entity.Student;

import java.util.HashMap;
import java.util.Map;

public class JacksonDemo {
    public static void main(String[] args) {

        ObjectMapper  mapper = new ObjectMapper();

        Student student = Student
                .builder()
                .id("24565441")
                .name("Hung Nguyen")
                .gpa(3.6)
                .build();

//        Manual
//        Map<String, Object> map = Map.of("student_id", student.getId(),
//                "name", student.getName(),
//                "gpa", student.getGpa());

//        Jackson
        Map<String, Object> map = mapper.convertValue(student, Map.class);

        System.out.println(map);
    }
}
