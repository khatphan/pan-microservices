package micro.core.product.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmptyMigrationStrategyConfig {

    //    private static final Log log = LogFactory.getLog("EmptyMigrationStrategyConfig");
    private static final Logger LOG = LoggerFactory.getLogger(EmptyMigrationStrategyConfig.class);

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            LOG.info("Skipping Flyway migration!");
        };
    }
}
