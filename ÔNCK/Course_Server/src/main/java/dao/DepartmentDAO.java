package dao;

import db.JPAutil;
import entity.Department;
import jakarta.persistence.EntityManager;

import java.util.*;

public class DepartmentDAO  {
    public Map<Department, Long> getNumberOfStudentsByDepartment(){
        EntityManager em = null;
        try {
            em= JPAutil.getEntityManager();
            String query= """
                    select d, count (s.student)from  Department d
                    join d.courses c
                    join c.studentGrades s
                    group by d
                    """;
            List<Object[]> result= em.createQuery(query,Object[].class).getResultList();
            Map<Department,Long> map = new HashMap<>();
            for(Object[] obj :result){
                map.put((Department) obj[0], (Long) obj[1]);
            }
            return  map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(em!=null)
                em.close();
        }
    }
    public List<Department> listDepartmentsWithoutStudents(){
        EntityManager em = null;
        try {
            em= JPAutil.getEntityManager();
            String query = """
                    SELECT d FROM Department d
                    where not exists (
                        select s from  Student s
                        join s.studentGrades sg
                        where sg.course.department=d
                    )
        """;
            return  em.createQuery(query,Department.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(em!=null)
                em.close();
        }
    }
}
