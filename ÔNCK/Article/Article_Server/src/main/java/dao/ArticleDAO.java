package dao;

import entity.Article;
import entity.ReviewSuggestion;

import java.util.List;

public interface ArticleDAO {
    List<Article> listArticlesByYIS(int year, String institutionName, ReviewSuggestion suggestion);
}
