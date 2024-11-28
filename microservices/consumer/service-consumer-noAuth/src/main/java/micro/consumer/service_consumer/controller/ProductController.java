package micro.consumer.service_consumer.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ProductController {

    private final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    public ProductController(WebClient webClient) {
        this.webClient = webClient;
    }

    private WebClient webClient;

    @GetMapping("/product/{id}")
    public ResponseEntity<String> getProductById(
            @PathVariable(name = "id") int productId) {

        String block = this.webClient
                .get()
                .uri("http://localhost:8090/product/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        LOG.info("===>>> This is block of webclient http://localhost:8090/product/{} : {}", productId, block);

        return ResponseEntity.ok(block);
    }

    /*@GetMapping("/test")
    public String[] getDefaultTest(
            @RegisteredOAuth2AuthorizedClient("test-client-passed")
                    OAuth2AuthorizedClient authorizedClient) {

        return this.webClient
                .get()
                .uri("http://localhost:8090/test/asd")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }*/

    @GetMapping("/test01")
    public String getDefaultTest01() {

        return this.webClient
                .get()
                .uri("http://localhost:8090/test/asd")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
