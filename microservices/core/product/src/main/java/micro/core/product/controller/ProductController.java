package micro.core.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import micro.core.product.model.Product;
import micro.core.product.service.ProductService;

@RestController
public class ProductController {

    @Value("${user.role}")
    private String role;

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProduct(@PathVariable int prodId) {

        return ResponseEntity.ok(productService.getProductById(prodId));
    }

    @GetMapping("/whoami/{username}")
    public String whoami(@PathVariable("username") String username) {
        return String.format("You're %s and you'll become a(n) %s...\n", username, role);
    }
}
