package dao;

import db.JPAUtil;
import entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MovieDao {
    public boolean addMovie(Movie movie){
        EntityManager em = null;
        EntityTransaction en = null;
        try {
            em= JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();
            em.persist(movie);
            en.commit();
            return true;
        } catch (Exception e) {
            if(en.isActive()&& en!=null){
                en.rollback();
                return  false;
            }
            throw new RuntimeException(e);
        }
        finally {
            if(em!= null){
                em.close();
            }
        }
    }
}
