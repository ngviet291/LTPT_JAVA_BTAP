package dao;

import db.JPAutil;
import entity.Student;
import jakarta.persistence.EntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO {
    public Map<Student,Double> getAverageScoreOfStudents(){
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            String query= """
                    select s, avg(sg.grade) from Student s
                    join s.studentGrades sg
                    where sg.grade is not  null
                    group by s
                    order by s.id
                    """;
            List<Object[]> result = em.createQuery(query,Object[].class).getResultList();
            Map<Student,Double> map = new HashMap<>();
            for(Object[] obj :result){
                map.put((Student) obj[0], (Double) obj[1]);
            }
            return  map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
    public List<Student> listStudentsStudyingCourseWithHighestScore(String courseName){
        EntityManager em = null;
        try {
            em = JPAutil.getEntityManager();
            String query= """
                    select s from Student s
                     join s.studentGrades sg
                     join sg.course c
                    where c.title=:name
                    and sg.grade = (
                          select max(sg2.grade) from StudentGrade sg2 where sg2.course = c
                    )
                    """;
            return em.createQuery(query, Student.class).setParameter("name",courseName).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
}
