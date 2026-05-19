package dao.impl;

import db.JPAUtil;
import entity.Show;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;

public class ShowDAOImpl {
    public List<Show> listShowsByCurrentDateAndDirector(String director){
        EntityManager entityManager= null;
        try {
            entityManager= JPAUtil.getEntityManager();
            String query= """
                    select s from Show s
                    join fetch s.movie m
                    where m.director like :name and FUNCTION('DATE',s.showDateTime)= current_date
                    """;
            return entityManager.createQuery(query,Show.class).setParameter("name","%"+director+"%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
    }
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime){
        EntityManager em= null;
        EntityTransaction en = null;
        try {
            em= JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();
            Show show = em.find(Show.class,showId);
            if(show==null){
                return  false;
            }
            show.setShowDateTime(newShowDateTime);
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
    public Show findById(String id){
        EntityManager entityManager= null;
        try {
            entityManager= JPAUtil.getEntityManager();
            String query = """
                    select s from Show s
                    left join fetch s.tickets t
                    where s.id= :id
                    """;
            return entityManager.createQuery(query, Show.class).setParameter("id",id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
    }

}
