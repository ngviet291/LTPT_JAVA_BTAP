/*
 * @ (#) StudentHandle.java     1.0    3/13/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package handle;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/13/2026
 * @version:    1.0
 */

import entity.Course;
import entity.Enrollment;
import entity.Student;
import jakarta.json.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentHandle {

    public static  List<Student> students (File file){
        JsonArray ja = Handle.readArray(file);
        List<Student> students = new ArrayList<>();

        for(JsonValue v :ja){
            JsonObject jo =v.asJsonObject();
            Student student = convertStudent(jo);
        students.add(student);
        }


    return students;
    }

    private static Student convertStudent(JsonObject jo) {
        JsonObjectBuilder builderStudent = Json.createObjectBuilder()  ;
        List<String> phones = new ArrayList<>();
        JsonArray jPhones = jo.getJsonArray("phones");
        jPhones.forEach(v->{
            JsonString phone = (JsonString)v;
            phones.add(phone.getString());

        });
        Set<Enrollment> enrollments = new HashSet<>();
        JsonArray jaEJsonArray = jo.getJsonArray("enrollments");
        jaEJsonArray.forEach(v->{
            JsonObject value = v.asJsonObject();
            JsonObject course = value.getJsonObject("course");
            Course course1 = Course.builder().id(course.getString("id"))
                    .name(course.getString("name")).credits(course.getInt("credits"))
                    .build();
            enrollments.add(new Enrollment(course1, value.getJsonNumber("grade").doubleValue()));
        });
        LocalDate date = convertLocalDate(jo.getJsonObject("dob"));
        return Student.builder().id(jo.getJsonNumber("id").longValue())
                .firstName(jo.getString("firstName")).lastName(jo.getString("lastName")).active(jo.getBoolean("isActive"))
                .phones(phones).enrollments(enrollments).dob(date)

                .build();
    }
    public static String toJson(Student student){
        JsonObject jo = fromJsonObject(student);
        return Handle.toJson(jo);
    }

    private static JsonObject fromJsonObject(Student S) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder ja = Json.createArrayBuilder();
        List<String>phones = S.getPhones();
        phones.forEach(p->{
            ja.add(p.toString());
        });
        return builder.add("id",S.getId()).add("firstName",S.getFirstName()).add("lastName",S.getLastName())
                .add("dob",date(S.getDob()))
                .add("isActive",S.isActive())
                .add("phones",ja.build())

                .build();
    }
    public static JsonObject date (LocalDate local){
        int nam =local.getYear();
        int thang =local.getMonthValue();
        int ngay =local.getDayOfMonth();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        return builder.add("year",nam).add("month",thang).add("day",ngay).build();

    }
    public static LocalDate convertLocalDate(JsonObject jo){
        int ngay =jo.getInt("day");
        int thang =jo.getInt("month");
        int nam =jo.getInt("year");

        return
                LocalDate.of(nam,thang,ngay);
    }


    public static void main(String[] args) {
        File file = new File("json/student.json");
        List<Student> students = StudentHandle.students(file);
        students.forEach(v-> System.out.println(toJson(v)));



    }
}
