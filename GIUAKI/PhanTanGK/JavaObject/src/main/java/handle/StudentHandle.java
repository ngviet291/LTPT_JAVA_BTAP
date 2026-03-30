/*
 * @ (#) StudentHandle.java     1.0    3/17/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package handle;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/17/2026
 * @version:    1.0
 */

import entity.Course;
import entity.Enrollment;
import entity.Student;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentHandle {
    public static List<Student> getStudentById(File file, String id){
        JsonArray ja = JsonHandler.readFromFile(file);
        List<Student> ds = new ArrayList<>();
        ja.forEach(v->{
            JsonObject jo = v.asJsonObject();
            Student student = fromFrom(jo);
            ds.add(student);
        });

        return ds;

    }

    private static Student fromFrom(JsonObject jo) {

        JsonObject date =jo.getJsonObject("dob");
        LocalDate localDate = LocalDate.of(date.getInt("year"),date.getInt("month")
                ,date.getInt("day")
        );
        JsonArray jaEnrollment =jo.getJsonArray("enrollments");
        List<Enrollment> enrollments = new ArrayList<>();
        jaEnrollment.forEach(v->{
            JsonObject en = v.asJsonObject();
            JsonObject courseObject =en.getJsonObject("course");
            Course course = Course.builder().name(courseObject.getString("name"))
                    .credits(courseObject.getInt("credits"))
                    .id(courseObject.getString("id"))
                    .build();

            Enrollment enrollment = Enrollment.builder().course(course).grade(en.getJsonNumber("grade").doubleValue()).build();
            enrollments.add(enrollment);


        });
        List<String> phones = new ArrayList<>();
        JsonArray phonesA = jo.getJsonArray("phones");
        phonesA.forEach(v->{
            JsonString a = (JsonString) v;
            phones.add(a.getString());

        });
        return Student.builder().id(jo.getJsonNumber("id").longValue())
                .firstName(jo.getString("firstName"))
                .lastName(jo.getString("lastName"))
                .dob(localDate)
                .active(jo.getBoolean("isActive"))
                .enrollments(enrollments)
                .phones(phones)
                  .build();
    }

    public static void main(String[] args) {
        List<Student>ds =getStudentById(new File("json/students.json"),"");
        ds.forEach(v-> System.out.println(v.toString()));

    }
}
