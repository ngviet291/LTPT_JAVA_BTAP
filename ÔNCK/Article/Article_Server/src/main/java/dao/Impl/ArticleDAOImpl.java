package dao.Impl;

import db.JPAUtil;
import entity.Article;
import entity.Review;
import entity.ReviewSuggestion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ArticleDAOImpl implements dao.ArticleDAO {
    @Override
    public List<Article> listArticlesByYIS(int year, String institutionName, ReviewSuggestion suggestion){
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManager();
            String query = """
                    select a from Article  a
                    join a.author au
                    join au.institution i
                    where a.year=:year and i.name=:name and a.reviewSuggestion=:suggestion
                    """;
            return em.createQuery(query, Article.class).setParameter("year",year)
                    .setParameter("name",institutionName)
                    .setParameter("suggestion",suggestion).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(em!=null)
                em.close();
        }

    }
}
