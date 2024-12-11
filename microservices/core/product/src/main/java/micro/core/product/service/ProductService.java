package micro.core.product.service;

import micro.core.product.dto.ProductDto;
import org.springframework.stereotype.Service;

public interface ProductService {

//    public Product getProductById(int id) {
//        return new Product(1, "Iphone 16 Promax", 489);
//    }

    ProductDto getProductById(int id);
}
