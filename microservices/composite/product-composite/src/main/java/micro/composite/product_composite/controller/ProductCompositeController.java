package micro.composite.product_composite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import micro.composite.product_composite.model.ProductAggregated;
import micro.composite.product_composite.service.ProductCompositeService;

@RestController
public class ProductCompositeController {

    @Autowired
    private ProductCompositeService productCompositeService;

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductAggregated> getProductById(@PathVariable(name = "id") int id) {

        ProductAggregated productAggregated = productCompositeService.getProductById(id);

        return ResponseEntity.ok(productAggregated);
    }

    @GetMapping("/circuit")
    public String checkCircuitBreaker() {

        return productCompositeService.failureWithFallback();
    }
}
