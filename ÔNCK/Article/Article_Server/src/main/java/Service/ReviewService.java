package Service;

import dao.Impl.ReviewDAOImpl;
import dto.ReviewDTO;
import entity.Article;
import entity.Review;

public class ReviewService {
    private ReviewDAOImpl reviewDAO = new ReviewDAOImpl();
    public boolean addNewReview(ReviewDTO reviewDTO){
        Article article = new Article();
        article.setId(reviewDTO.getArticleDTO().getId());

        Review review = Review.builder().id(reviewDTO.getId()).comment(reviewDTO.getComment())
                .reviewDays(reviewDTO.getReviewDays())
                .reviewDate(reviewDTO.getReviewDate())
                .reviewer(reviewDTO.getReviewer())
                .article(article)
                .build();
        return reviewDAO.addNewReview(review);
    }
    public boolean updateReview(ReviewDTO reviewDTO){
        Review review = Review.builder().id(reviewDTO.getId()).comment(reviewDTO.getComment())
                .reviewDays(reviewDTO.getReviewDays())
                .reviewDate(reviewDTO.getReviewDate())
                .reviewer(reviewDTO.getReviewer())

                .build();
        return reviewDAO.updateReview(review);
    }
    public double calculateAverageReviewDays(){
        return  reviewDAO.calculateAverageReviewDays();
    }
}
