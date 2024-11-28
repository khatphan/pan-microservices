package micro.api.resource_server.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "productcomposite")
public interface ProductComposite {

    /*@GetMapping("/product/{id}")
    public ResponseEntity<ProductAggregated> getProductById(@PathVariable(name = "id") int id);*/
}
