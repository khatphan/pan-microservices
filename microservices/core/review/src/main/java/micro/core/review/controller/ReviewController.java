package micro.core.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import micro.core.review.model.Review;
import micro.core.review.service.ReviewService;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(@RequestParam(name = "prodId") int prodId) {

        List<Review> resultReview = reviewService.getReviews(prodId);

        return ResponseEntity.ok(resultReview);
    }
}
