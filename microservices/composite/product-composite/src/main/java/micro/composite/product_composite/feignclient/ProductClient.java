package micro.composite.product_composite.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import micro.composite.product_composite.dto.ProductDto;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/product/{prodId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable int prodId);
}
