package micro.api.resource_server.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class ProductApiController {

    private final Logger LOG = LoggerFactory.getLogger(ProductApiController.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LoadBalancerClient loadBalanced;

    @GetMapping("/product/{productId}")
    @CircuitBreaker(name = "DEFAULT_prod", fallbackMethod = "getProductByIdFallBack")
    public ResponseEntity<String> getProductById(@PathVariable int productId) {

        /*LOG.info(
        "ProductApi: User={}, Auth={}, called with productId={}",
        currentUser.getName(),
        authorizationHeader,
        productId);*/
        LOG.info("START getProductById ---");

        URI uri = loadBalanced.choose("productcomposite-service").getUri();
        String url = uri.toString() + "/product/" + productId;
        LOG.info("uri from loadBalanced.choose = {}, and url = {}", uri, url);

        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> getProductByIdFallBack(@PathVariable(name = "id") int productId) {

        /*LOG.warn(
        "Using fallback method for product-composite-service. User={}, Auth={}, called with productId={}",
        currentUser.getName(),
        authorizationHeader,
        productId);*/

        return new ResponseEntity<String>("BAD_GATEWAY", HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/test/asd")
    public String[] getDefaultTest() {

        return new String[] {"Welcome to ", "ProductAPI controller"};
    }
}
