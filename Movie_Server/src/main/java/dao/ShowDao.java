package dao;

import db.JPAUtil;
import entity.Show;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;

public class ShowDao {
    public List<Show> listShowsByCurrentDateAndDirector(String director){
        EntityManager em = null;
        try {
            em= JPAUtil.getEntityManager();
            String query= """
                    SELECT s from Show s
                    join s.movie m
                    where m.director like :name AND
                    FUNCTION('DATE',s.showDateTime) = current_date 
                    """;
            return em.createQuery(query,Show.class).setParameter("name","%"+director+"%").getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(em !=null)
                em.close();
        }
    }
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime){
        EntityTransaction en= null;
        EntityManager em= null;
        try {
            em= JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();
            String query= """
                    UPDATE Show s
                    set s.showDateTime= :newShowDate
                    where s.id= :showId
                    """;
            em.createQuery(query).setParameter("newShowDate",newShowDateTime)
                    .setParameter("showId",showId).executeUpdate();
//            Show show = em.find(Show.class,showId);
//            show.setShowDateTime(newShowDateTime);
//            em.merge(show);
            en.commit();
            return true;
        } catch (Exception e) {
            if(en.isActive()&& en!=null){
            en.rollback();
                return false;
        }
            throw new RuntimeException(e);


        }
        finally {
            if(em!=null)
                em.close();
        }
    }
}
