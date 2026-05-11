package dao.impl;

import dao.MovieDao;
import db.JPAUtil;
import entity.Movie;
import jakarta.persistence.EntityManager;

public class MovieImpl implements MovieDao {

    @Override
    public boolean addMovie(Movie movie) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(movie);

            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction() != null
                    && em.getTransaction().isActive()) {

                em.getTransaction().rollback();
            }

            e.printStackTrace();

            return false;

        } finally {
            em.close();
        }
    }
}