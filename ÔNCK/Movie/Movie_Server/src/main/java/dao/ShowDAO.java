package dao;

import db.JPAUtil;
import entity.Show;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;

public class ShowDAO {
    public List<Show> listShowsByCurrentDateAndDirector(String director){
        EntityManager em = null;
        try{
            em= JPAUtil.getEntityManager();
            String query= """
                    SELECT s from Show s
                    join fetch s.movie m
                    where m.director like :name and function("DATE",s.showDateTime) = current_date
                    """;
            return em.createQuery(query,Show.class).setParameter("name","%"+director+"%").getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime){
        EntityManager em = null;
        EntityTransaction en = null;
         try {
             em= JPAUtil.getEntityManager();
             en= em.getTransaction();
             en.begin();
             Show show = em.find(Show.class,showId);
             show.setShowDateTime(newShowDateTime);
             en.commit();
             return true;
         } catch (Exception e) {
             if(en!=null&&en.isActive()){
                 en.rollback();
                 return  false;
             }
             throw new RuntimeException(e);
         }finally {
             if(em!=null){
                 em.close();
             }
         }
    }
    public Show findById(String id){
        EntityManager em =null;
        try {
            em= JPAUtil.getEntityManager();
//            String query= """
//                    select s from Show s left join s.tickets where s.id=:id
//                    """;
//            return em.createQuery(query,Show.class).setParameter("id",id).getSingleResult();
            return em.find(Show.class,id);
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
