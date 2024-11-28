package micro.core.product.service;

import org.springframework.stereotype.Service;

import micro.core.product.model.Product;

@Service
public class ProductService {

    public Product getProductById(int id) {
        return new Product(1, "Iphone 16 Promax", 489);
    }
}
