package io.a97lynk.cityservice;

import io.a97lynk.cityservice.config.database.DataSourceConfig;
import io.a97lynk.cityservice.config.database.DataSourceConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CityServiceApplication.class, args);
    }

    @Autowired
    DataSourceConfigRepository dataSourceConfigRepository;

    @Override
    public void run(String... args) throws Exception {

        if (dataSourceConfigRepository.findAll().size() == 2) return;

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverClassName("org.postgresql.Driver");
        dataSourceConfig.setInitialize(true);
        dataSourceConfig.setName("tenant1");
        dataSourceConfig.setUsername("postgres");
        dataSourceConfig.setPassword("awhjw");
        dataSourceConfig.setUrl("jdbc:postgresql://localhost:5432/tenant1?ApplicationName=MultiTenant");

        dataSourceConfigRepository.save(dataSourceConfig);

        dataSourceConfig.setId(null);
        dataSourceConfig.setName("tenant2");
        dataSourceConfig.setUrl("jdbc:postgresql://localhost:5432/tenant2?ApplicationName=MultiTenant");
        dataSourceConfigRepository.save(dataSourceConfig);

    }
}
