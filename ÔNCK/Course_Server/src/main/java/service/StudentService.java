package service;

import dao.StudentDAO;
import entity.Student;

import java.util.List;
import java.util.Map;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();
    public Map<Student,Double> getAverageScoreOfStudents(){
        return studentDAO.getAverageScoreOfStudents();
    }
    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName) {
        return studentDAO.listStudentsStudyingCourseWithHighestScore(courseName);
    }
}
