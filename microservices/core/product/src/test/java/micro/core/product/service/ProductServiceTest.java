package micro.core.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import micro.core.product.dto.ProductDto;
import micro.core.product.entities.Product;
import micro.core.product.repo.ProductRepo;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceTest.class);

    @Mock
    private ProductRepo productRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private ProductDto productDto;
    private static final int idProduct = 1;

    @BeforeAll
    public void initSetup() {
        productDto = new ProductDto();
        productDto.setProductId(1);
        productDto.setName("Halland");
        productDto.setWeight(566);

        LOG.info("simple test");
    }

    @Test
    public void getProductById() {

        Product product = new Product(1, "Halland", 566);
        modelMapper.map(product, ProductDto.class);
        when(productRepo.findById(anyInt())).thenReturn(Optional.of(product));

        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        assertEquals(productDto, productServiceImpl.getProductById(1));
    }

    @Test
    public void getProductByIdFallBackTest() {
        Exception notFoundException = new RuntimeException("Test exception message");

        ProductDto productDto1 = new ProductDto();
        when(productRepo.findById(idProduct)).thenThrow(notFoundException);

        assertNotEquals(productDto, productServiceImpl.getProductByIdFallBack(idProduct, notFoundException));
    }
}
