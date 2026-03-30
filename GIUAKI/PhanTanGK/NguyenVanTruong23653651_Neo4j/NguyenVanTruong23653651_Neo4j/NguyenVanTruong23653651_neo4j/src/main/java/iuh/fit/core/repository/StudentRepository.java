package iuh.fit.core.repository;

import iuh.fit.core.entity.Student;

import java.util.List;

public interface StudentRepository {
    Student findStudentById(String studentId);
    boolean createStudent(Student student);
    boolean updateStudent(String student_id,String name);
    boolean deleteStudent(String studentId);
    List<Student> getStudents(int page, int size);
    boolean updateStudent1(String id,Student st);
}
