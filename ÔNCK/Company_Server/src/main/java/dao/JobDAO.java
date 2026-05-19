package dao;

import db.JPAUtil;
import entity.Job;
import entity.JobStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JobDAO {
    public boolean addJob(Job job){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em= JPAUtil.getEntityManager();
            et= em.getTransaction();
            et.begin();

            em.persist(job);

            et.commit();
            return true;
        } catch (Exception e) {
            if(et!=null&&et.isActive()){
                et.rollback();
                return false;
            }
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
    public boolean updateJob(Job job){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em= JPAUtil.getEntityManager();
            et= em.getTransaction();
            et.begin();

            Job job1 = em.find(Job.class,job.getId());

            if(job1==null){
                return false;
            }
            job1.setSalary(job.getSalary());

            et.commit();
            return true;
        } catch (Exception e) {
            if(et!=null&&et.isActive()){
                et.rollback();
                return false;
            }
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
    public boolean deleteJob(String id){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em= JPAUtil.getEntityManager();
            et= em.getTransaction();
            et.begin();

            Job job1 = em.find(Job.class,id);

            if(job1==null){
                return false;
            }
            em.remove(job1);
            et.commit();
            return true;
        } catch (Exception e) {
            if(et!=null&&et.isActive()){
                et.rollback();
                return false;
            }
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null){
                em.close();
            }
        }
    }
    public Map<Job, Long> countPerJobByCompany(String companyName){
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();

            String query= """
                    select j,count(a)  from Job j
                    join j.company c
                    join j.applications a
                    where c.name=:companyName
                    group by j
                    order by count(a) desc
                    """;
            List<Object[]> jobs= em.createQuery(query,Object[].class).setParameter("companyName",companyName).getResultList();
            Map<Job, Long> map = new LinkedHashMap<>();
            for(Object[] obj: jobs){
                map.put((Job) obj[0], (Long) obj[1]);
            }

            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }

}
