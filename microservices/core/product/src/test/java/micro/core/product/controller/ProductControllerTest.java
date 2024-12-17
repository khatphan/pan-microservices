package micro.core.product.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import micro.core.product.dto.ProductDto;
import micro.core.product.service.ProductService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private static final String role = "User";

    private ProductDto productDto;
    private static final int idProduct = 1;

    @BeforeAll
    public void initSetup() {
        productDto = new ProductDto();
        productDto.setProductId(1);
        productDto.setName("Halland");
        productDto.setWeight(566);
    }

    @Test
    void getProduct() {
        when(productService.getProductById(idProduct)).thenReturn(productDto);
        assertNotNull(productController.getProduct(idProduct));
    }

    @Test
    void whoami() {
        assertNotNull(productController.whoami("name"));
    }
}
