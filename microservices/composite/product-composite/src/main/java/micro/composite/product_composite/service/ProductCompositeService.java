package micro.composite.product_composite.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import micro.composite.product_composite.dto.ProductDto;
import micro.composite.product_composite.dto.ReviewDto;
import micro.composite.product_composite.feignclient.ProductClient;
import micro.composite.product_composite.feignclient.ReviewClient;
import micro.composite.product_composite.model.ProductAggregated;
import micro.composite.product_composite.model.ReviewSummary;

@Service
public class ProductCompositeService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeService.class);

    private static final String DEFAULT_CIRCUIT = "default";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewClient reviewClient;

    @Autowired
    private ProductClient productClient;

    @CircuitBreaker(name = DEFAULT_CIRCUIT, fallbackMethod = "getProductByIdFallBack")
    public ProductAggregated getProductById(int id) {

        ProductDto productDto = getProductDtoUsingFegnClient(id);
        List<ReviewDto> reviewDtoList = getReviewDtoListUsingFeignClient(id);

        List<ReviewSummary> reviewSummaries = new ArrayList<>();
        if (reviewDtoList != null) {
            reviewSummaries = reviewDtoList.stream()
                    .map(r -> modelMapper.map(r, ReviewSummary.class))
                    .toList();
        }

        return new ProductAggregated(productDto, reviewSummaries);
    }

    public ProductAggregated getProductByIdFallBack(int id, Exception ex) {
        LOG.warn("===>>> Fall back method: defaultGetReview with productId = " + id + "\n" + ex.getMessage());
        return new ProductAggregated();
    }

    public List<ReviewDto> getReviewDtoListUsingFeignClient(int prodId) {
        ResponseEntity<List<ReviewDto>> reviews = reviewClient.getReviews(prodId);

        List<ReviewDto> reviewDtos = null;
        if (reviews.getStatusCode().is2xxSuccessful()) {
            reviewDtos = reviews.getBody();
        } else {
            LOG.debug("Call to getReviews failed: {}", reviews.getStatusCode());
        }
        return reviewDtos;
    }

    public ProductDto getProductDtoUsingFegnClient(int prodId) {
        ResponseEntity<ProductDto> product = productClient.getProduct(prodId);
        if (product.getStatusCode().is2xxSuccessful()) {
            return product.getBody();
        } else {
            LOG.debug("Call to getProduction failed: {}", product.getStatusCode());
        }
        return product.getBody();
    }

    @CircuitBreaker(name = DEFAULT_CIRCUIT, fallbackMethod = "fallback")
    public String failureWithFallback() {
        return failure();
    }

    @CircuitBreaker(name = DEFAULT_CIRCUIT)
    public String failure() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    private String fallback(Exception ex) {
        return "===>>> fallback >>> Recovered: " + ex.toString();
    }
}
