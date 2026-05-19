package dao;

import db.JPAUtil;
import entity.Company;
import jakarta.persistence.EntityManager;

public class CompanyDAO {

    public Company findById(String id){
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            return em.find(Company.class,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }
}
