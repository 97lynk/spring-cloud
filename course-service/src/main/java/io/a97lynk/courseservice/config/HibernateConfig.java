package io.a97lynk.courseservice.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(FlywayProperties.class)
@Slf4j
public class HibernateConfig {

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            JpaProperties jpaProperties,
            MultiTenantConnectionProvider multiTenantConnectionProviderImpl,
            CurrentTenantIdentifierResolver currentTenantIdentifierResolverImpl
    ) {

        Map<String, Object> jpaPropertiesMap = new HashMap<>(jpaProperties.getProperties());
        jpaPropertiesMap.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        jpaPropertiesMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProviderImpl);
        jpaPropertiesMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolverImpl);
        jpaPropertiesMap.put(Environment.DIALECT, PostgreSQL95Dialect.class.getName());
        jpaPropertiesMap.put(Environment.FORMAT_SQL, true);
        jpaPropertiesMap.put(Environment.SHOW_SQL, true);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("io.a97lynk*");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaPropertyMap(jpaPropertiesMap);
        return em;
    }


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
}
