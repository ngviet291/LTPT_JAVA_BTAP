package dao.impl;

import db.JPAUtil;
import entity.Doctor;
import jakarta.persistence.EntityManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DoctorDAOImpl implements dao.DoctorDAO {
    @Override
    public List<Doctor> getDoctorsByDepartment(String deptName){
        EntityManager entityManager = null;
        try {
            entityManager= JPAUtil.getEntityManager();
            String query= """
                        Select d from Doctor d
                        join d.treatments tr
                        JOIN tr.department dp
                        where dp.name=:name
                    """;

            return entityManager.createQuery(query,Doctor.class).setParameter("name",deptName).getResultList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }
    @Override
    public Map<Doctor,Integer> getNoTreatmentsByDoctors(int month, int year){
        EntityManager em= null;
        try {
            em= JPAUtil.getEntityManager();
            String query= """
                    select t.doctor,count(t) from Treatment t
                    where month(t.startDate)=:month
                    and year (t.startDate)=:year
                    group by t.doctor
                    order by t.doctor.speciality
                    """;
            List<Object[]> result= em.createQuery(query, Object[].class).setParameter("month",month).setParameter("year",year).getResultList();
            Map<Doctor,Integer> map = new LinkedHashMap<>();
            for(Object[] obj: result){
                //Ep kieu tu long thanh integer vi count tra ve long
                map.put((Doctor) obj[0],((Long) obj[1]).intValue());
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null)
                em.close();
        }
    }
}
