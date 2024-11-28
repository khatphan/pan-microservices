package micro.core.review.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import micro.core.review.model.Review;

@Service
public class ReviewService {

    public List<Review> getReviews(int prodId) {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review(prodId, 1, "Author 1", "Subject 1", "Content... 1"));
        reviewList.add(new Review(prodId, 2, "Author 2", "Subject 2", "Content... 2"));
        reviewList.add(new Review(prodId, 3, "Author 3", "Subject 3", "Content... 3"));

        return reviewList;
    }
}
