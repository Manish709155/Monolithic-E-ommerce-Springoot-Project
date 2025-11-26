package com.ecommerce.app.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.ecommerce.app")
public class MultiDbConfig {

    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = {"entityManagerFactory", "mysqlEntityManagerFactory"})
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mysqlDataSource);
        em.setPackagesToScan("com.ecommerce.app");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", true);
        props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/ecommerce_application");
        props.put("jakarta.persistence.jdbc.user", "root");
        props.put("jakarta.persistence.jdbc.password", "root");
        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");

        em.setJpaPropertyMap(props);
        em.setPersistenceUnitName("mysqlPU");
        return em;
    }


    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }



    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            @Qualifier("postgresDataSource") DataSource postgresDataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource);
        em.setPackagesToScan("com.ecommerce.app");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", true);
        props.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/ecommerce_application");
        props.put("jakarta.persistence.jdbc.user", "postgres");
        props.put("jakarta.persistence.jdbc.password", "root");
        props.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");

        em.setJpaPropertyMap(props);
        em.setPersistenceUnitName("postgresPU");
        return em;
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}