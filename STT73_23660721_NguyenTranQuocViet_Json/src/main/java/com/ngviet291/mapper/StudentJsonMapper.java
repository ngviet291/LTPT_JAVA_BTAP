package com.ngviet291.mapper;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.List;

import com.ngviet291.entity.Student;

import jakarta.json.*;

//JAVA OBJECT MODAL
public class StudentJsonMapper {
    public static Student fromJson(File jsonFile) {
        // Implement JSON parsing logic here
        try(
            FileReader fr= new FileReader(jsonFile);
            JsonReader jsonReader= Json.createReader(fr);
        ) {
            JsonObject jo= jsonReader.readObject();
            long id= jo.getJsonNumber("id").longValue();
            boolean isActive= jo.getBoolean("isActive");
            JsonObject dobObj= jo.getJsonObject("dob");
            int year= dobObj.getInt("year");
            int month= dobObj.getInt("month");
            int day= dobObj.getInt("day");
            JsonArray phonesArray= jo.getJsonArray("phones");
            List<String> phones= phonesArray.stream()
                                        .map(e-> e.toString().replace("\"", ""))
                                        .toList();
            return Student.builder()
                    .id(id).firstName(jo.getString("firstName")).lastName(jo.getString("lastName"))
                    .dob((java.time.LocalDate.of(year, month, day))).phones(phones)
                    .isActive(isActive)
                    .build();
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }
    
    public static Student fromJson(String json) {
        // Implement JSON parsing logic here
        return null;
    }
    public static String toJson(Student student) {
        // Implement JSON parsing logic here
        try(
                StringWriter sw= new StringWriter();
                JsonWriter jsonWriter= Json.createWriter(sw);
        ){
            JsonObjectBuilder builder= Json.createObjectBuilder();
            JsonObject dobObj= Json.createObjectBuilder()
                    .add("year", student.getDob().getYear())
                    .add("month", student.getDob().getMonthValue())
                    .add("day", student.getDob().getDayOfMonth())
                    .build();

            JsonArray phonesArrayBuilder= Json.createArrayBuilder(student.getPhones()).build();
            JsonObject jo= builder
                    .add("id", student.getId())
                    .add("firstName", student.getFirstName())
                    .add("lastName", student.getLastName())
                    .add("isActive", student.isActive())
                    .add("dob", dobObj)
                    .add("phones", phonesArrayBuilder)
                    .build();
            jsonWriter.writeObject(jo);
            return jo.toString();
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
    public static void toJson(Student student, File file) {
        // Implement JSON serialization logic here

    }
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(200L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setActive(true);
        student.setDob(java.time.LocalDate.of(2000, 5, 15));
        student.setPhones(List.of("123-456-7890", "987-654-3210"));

        String json = toJson(student);
        System.out.println(json);
//        Student student = fromJson(new File("json/student.json"));
//        System.out.println(student);
    }
}
