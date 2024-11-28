package micro.composite.product_composite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int productId;
    private int reviewId;
    private String author;
    private String subject;
    private String content;
}
