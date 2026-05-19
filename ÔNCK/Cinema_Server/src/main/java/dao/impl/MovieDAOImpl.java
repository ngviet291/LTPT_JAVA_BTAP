package dao.impl;

import db.JPAUtil;
import entity.Movie;
import entity.Show;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MovieDAOImpl {
    public boolean addMovie(Movie movie){
        EntityManager em= null;
        EntityTransaction en = null;
        try {
            em= JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();
            em.persist(movie);
            en.commit();
            return true;
        } catch (Exception e) {
            if(en!=null && en.isActive()){
                en.rollback();
                return false;
            }
            throw new RuntimeException(e);
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }
}
