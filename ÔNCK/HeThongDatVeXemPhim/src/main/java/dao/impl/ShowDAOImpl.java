/*
 * @ (#) ShowDAOImpl.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package dao.impl;


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import dao.ShowDAO;
import db.JPAUtil;
import entity.Show;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ShowDAOImpl implements ShowDAO {
    @Override
    public List<Show> listShowByCurrentDateAndDirector(String director) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            LocalDateTime startDate = LocalDate.now().atStartOfDay();
            LocalDateTime endDate = startDate.plusDays(1);

            String query = "SELECT s FROM Show s JOIN s.movie m " +
                    "WHERE m.director LIKE :director AND s.showDateTime >= :startDateCurrent " +
                    "AND s.showDateTime < :endDateCurrent";
            TypedQuery<Show> result = em.createQuery(query, Show.class);
            result.setParameter("director", "%" + director + "%");
            result.setParameter("startDateCurrent", startDate);
            result.setParameter("endDateCurrent", endDate);
            return result.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }

    }

    @Override
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Show show = em.find(Show.class, showId);
            if (show == null) {
                em.getTransaction().rollback();
                return false;
            }

            String query = "SELECT COUNT(t) FROM Ticket t WHERE t.show.id=:showId";
            Long ticketCount = em.createQuery(query, Long.class)
                    .setParameter("showId", showId)
                    .getSingleResult();

            if(ticketCount == 0){
                show.setShowDateTime(newShowDateTime);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;

        } finally {
            em.close();
        }
    }


}
