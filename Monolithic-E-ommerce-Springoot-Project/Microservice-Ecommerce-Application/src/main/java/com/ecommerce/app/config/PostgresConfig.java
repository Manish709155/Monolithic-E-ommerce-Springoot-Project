//package com.ecommerce.app.config;
//
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.ecommerce.app.repository",
//        entityManagerFactoryRef = "pgEntityManagerFactory",
//        transactionManagerRef = "pgTransactionManager"
//)
//public class PostgresConfig {
//
//    @Bean(name = "pgDataSource")
//    @ConfigurationProperties(prefix = "spring.postgresql.datasource")
//    public DataSource pgDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "pgEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean pgEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("pgDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.ecommerce.app.entity")
//                .persistenceUnit("postgres")
//                .build();
//    }
//
//    @Bean(name = "pgTransactionManager")
//    public PlatformTransactionManager pgTransactionManager(
//            @Qualifier("pgEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}




// multi db config

//package com.ecommerce.app.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.*;
//import org.springframework.orm.jpa.*;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EntityScan(basePackages = "com.ecommerce.app")
//public class MultiDbConfig {
//
//    // MySQL
//    @Primary
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("mysqlDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.ecommerce.app")
//                .persistenceUnit("primary")
//                .build();
//    }
//
//
//    @Primary
//    @Bean(name = "mysqlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
//            @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
//
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(mysqlDataSource);
//        em.setPackagesToScan("com.ecommerce.app");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        props.put("hibernate.hbm2ddl.auto", "update");
//        props.put("hibernate.show_sql", true);
//        props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/ecommerce_application2");
//        props.put("jakarta.persistence.jdbc.user", "root");
//        props.put("jakarta.persistence.jdbc.password", "root");
//        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
//
//        em.setJpaPropertyMap(props);
//        em.setPersistenceUnitName("mysqlPU");
//        return em;
//    }
//
//    @Primary
//    @Bean(name = "mysqlTransactionManager")
//    public PlatformTransactionManager mysqlTransactionManager(
//            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//
//    // PostgreSQL
//    @Bean(name = "postgresDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.postgres")
//    public DataSource postgresDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "postgresEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
//            @Qualifier("postgresDataSource") DataSource postgresDataSource) {
//
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(postgresDataSource);
//        em.setPackagesToScan("com.ecommerce.app");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        props.put("hibernate.hbm2ddl.auto", "update");
//        props.put("hibernate.show_sql", true);
//        props.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/ecommerce_application");
//        props.put("jakarta.persistence.jdbc.user", "postgres");
//        props.put("jakarta.persistence.jdbc.password", "root");
//        props.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
//
//        em.setJpaPropertyMap(props);
//        em.setPersistenceUnitName("postgresPU");
//        return em;
//    }
//
//    @Bean(name = "postgresTransactionManager")
//    public PlatformTransactionManager postgresTransactionManager(
//            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//}

// multi db config 2

//package com.ecommerce.app.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.*;
//import org.springframework.orm.jpa.*;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EntityScan(basePackages = "com.ecommerce.app")
//public class MultiDbConfig {
//
//    // ========================
//    // ✅ Primary: MySQL Config
//    // ========================
//
//    @Primary
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = {"entityManagerFactory", "mysqlEntityManagerFactory"}) // ✅ Alias added
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
//            @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
//
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(mysqlDataSource);
//        em.setPackagesToScan("com.ecommerce.app");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        props.put("hibernate.hbm2ddl.auto", "update");
//        props.put("hibernate.show_sql", true);
//        props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/ecommerce_application");
//        props.put("jakarta.persistence.jdbc.user", "root");
//        props.put("jakarta.persistence.jdbc.password", "root");
//        props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
//
//        em.setJpaPropertyMap(props);
//        em.setPersistenceUnitName("mysqlPU");
//        return em;
//    }
//
//    // ✅ Default TransactionManager
//    @Primary
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//
//    // =============================
//    // ✅ Secondary: PostgreSQL Config
//    // =============================
//
//    @Bean(name = "postgresDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.postgres")
//    public DataSource postgresDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "postgresEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
//            @Qualifier("postgresDataSource") DataSource postgresDataSource) {
//
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(postgresDataSource);
//        em.setPackagesToScan("com.ecommerce.app");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        props.put("hibernate.hbm2ddl.auto", "update");
//        props.put("hibernate.show_sql", true);
//        props.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/ecommerce_application");
//        props.put("jakarta.persistence.jdbc.user", "postgres");
//        props.put("jakarta.persistence.jdbc.password", "root");
//        props.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
//
//        em.setJpaPropertyMap(props);
//        em.setPersistenceUnitName("postgresPU");
//        return em;
//    }
//
//    @Bean(name = "postgresTransactionManager")
//    public PlatformTransactionManager postgresTransactionManager(
//            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//}
