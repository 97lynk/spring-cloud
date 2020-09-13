package io.a97lynk.studentservice;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"io.a97lynk"})
@EnableConfigurationProperties(FlywayProperties.class)
@Slf4j
public class StudentServiceApplication {


    // manual migrate multiple schemas
    @Bean
    public List<String> initFlyway(FlywayProperties flywayProperties, DataSource dataSource) throws Exception {
        List<String> schemas = flywayProperties.getSchemas();
        for (int i = 0; i < schemas.size(); i++) {
            log.info("Migrating data for schema: " + schemas.get(i));
            Flyway flyway = Flyway.configure()
                    .locations(flywayProperties.getLocations().get(i))
                    .baselineOnMigrate(Boolean.TRUE)
                    .dataSource(dataSource)
                    .schemas(schemas.get(i))
                    .load();

            flyway.migrate();
        }

        return schemas;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

}
