package dao;

import entity.Review;

public interface ReviewDAO {
    boolean addNewReview(Review review);

    boolean updateReview(Review review);

    double calculateAverageReviewDays();
}
