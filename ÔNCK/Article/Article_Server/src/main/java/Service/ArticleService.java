package Service;

import dao.Impl.ArticleDAOImpl;
import dto.ArticleDTO;
import entity.Article;
import entity.ReviewSuggestion;

import java.util.List;

public class ArticleService {
    private ArticleDAOImpl articleDAO = new ArticleDAOImpl();
    public List<ArticleDTO> listArticlesByYIS(int year, String institutionName, ReviewSuggestion suggestion){
        return  articleDAO.listArticlesByYIS(year,institutionName,suggestion).stream().map(this::toDTO).toList();
    }
    public ArticleDTO toDTO(Article article){
        return ArticleDTO.builder().id(article.getId()).title(article.getTitle())
                .reviewSuggestion(article.getReviewSuggestion())
                .year(article.getYear()).build();
    }
}
