package dao.Impl;

import db.JPAUtil;
import entity.Review;
import entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ReviewDAOImpl implements dao.ReviewDAO {
    @Override
    public boolean addNewReview(Review review){
        EntityManager em = null;
        EntityTransaction en = null;
        try {
            em = JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();
            
            // Gắn Article managed từ DB tiên không article_id sẽ null
            if (review.getArticle() != null && review.getArticle().getId() != null) {
                Article managedArticle = em.find(Article.class, review.getArticle().getId());
                if (managedArticle == null) {
                    throw new IllegalArgumentException("Article not found with id: " + review.getArticle().getId());
                }
                review.setArticle(managedArticle);
            }
            
            em.persist(review);
            en.commit();

            return true;
        } catch (Exception e) {
            if(en!=null&& en.isActive()){
                en.rollback();
                return  false;
            }
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null)
                em.close();
        }
    }
    @Override
    public boolean updateReview(Review review){
        EntityManager em = null;
        EntityTransaction en = null;
        try {
            em = JPAUtil.getEntityManager();
            en= em.getTransaction();
            en.begin();
            Review review1 = em.find(Review.class,review.getId());
            if (review1 == null) {
                return false;
            }

            review1.setComment(review.getComment());
            review1.setReviewer(review.getReviewer());
            review1.setReviewDate(review.getReviewDate());
            review1.setReviewDays(review.getReviewDays());

            en.commit();
            return true;
        } catch (Exception e) {
            if(en!=null&& en.isActive()){
                en.rollback();
                return  false;
            }
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null)
                em.close();
        }
    }
    @Override
    public double calculateAverageReviewDays(){
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            String query = """
                    select avg(r.reviewDays) from Review r
                    """;
            return em.createQuery(query, Double.class).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null)
                em.close();
        }
    }
}
