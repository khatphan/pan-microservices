package micro.core.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {

    /*@Profile("pan_rabbitmq")
    @Bean
    public CommandLineRunner usage(){
    	return args -> {
    		System.out.println("This app uses Spring Profiles to control its behavior.\n");
    		System.out.println("Sample usage: java -jar rproduct-0.0.1-SNAPSHOT.jar " +
    								"--spring.profiles.active=hello-rabbitmq,sender");
    	};
    }

    @Profile("!pan_rabbitmq")
    @Bean
    public CommandLineRunner tutorial(){
    	return new RabbitAmqpTutorialsRunner();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
