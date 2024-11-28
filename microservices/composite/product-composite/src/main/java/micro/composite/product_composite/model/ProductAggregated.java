package micro.composite.product_composite.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.composite.product_composite.dto.ProductDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAggregated {

    private int productId;
    private String name;
    private int weight;
    private List<ReviewSummary> reviews;

    public ProductAggregated(ProductDto product, List<ReviewSummary> reviews) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.weight = product.getWeight();
        this.reviews = reviews;
    }
}
