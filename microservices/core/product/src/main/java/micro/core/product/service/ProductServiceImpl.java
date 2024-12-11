package micro.core.product.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.ws.rs.NotFoundException;
import micro.core.product.dto.ProductDto;
import micro.core.product.entities.Product;
import micro.core.product.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final String DEFAULT_CIRCUIT = "default";

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @CircuitBreaker(name = DEFAULT_CIRCUIT, fallbackMethod = "getProductByIdFallBack")
    public ProductDto getProductById(int id) {

        Product productEntity = productRepo.findById(id).get();

        return modelMapper.map(productEntity, ProductDto.class);
    }

    public ProductDto getProductByIdFallBack(int id, Exception exception){
        LOG.warn("Your " + id + " input not found! Please input another ID! \n" + exception.getMessage());
        return new ProductDto();
    }
}
