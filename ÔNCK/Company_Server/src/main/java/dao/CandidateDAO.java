package dao;

import db.JPAUtil;
import entity.Company;
import entity.JobStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CandidateDAO {
    public List<Object[]> findBySkillInOpenJobs(String skill){
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();

            String query= """
                    select c,j.title, a.appliedDate from Candidate c
                    join c.applications a
                    join a.job j
                    join j.skills s
                    where s.name=:skill and j.status=:status
                    """;

            return em.createQuery(query,Object[].class).setParameter("skill",skill)
                    .setParameter("status", JobStatus.OPEN).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }
}
