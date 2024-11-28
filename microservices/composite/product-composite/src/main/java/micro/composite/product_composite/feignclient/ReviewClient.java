package micro.composite.product_composite.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import micro.composite.product_composite.dto.ReviewDto;

@FeignClient(name = "review-service")
public interface ReviewClient{

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(@RequestParam(name = "prodId") int prodId);
}
