package com.personal.common.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:socket-io-db.properties"})
@EnableJpaRepositories(basePackages = "com.personal.common.core.repo",
        entityManagerFactoryRef = "socketIoEntityManagerFactory",
        transactionManagerRef = "socketIoTransactionManager")
@EnableTransactionManagement
public class SocketIoSourceConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.socketIo.datasource")
    public DataSource socketIoDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("app.socketIo.datasource.jdbcUrl"));
        dataSource.setUsername(environment.getProperty("app.socketIo.datasource.username"));
        dataSource.setPassword(environment.getProperty("app.socketIo.datasource.password"));

        dataSource.setConnectionTimeout(Long.parseLong(
                Objects.requireNonNull(environment.getProperty("app.socketIo.datasource.hikari.connectionTimeout")))
        );
        dataSource.setIdleTimeout(Long.parseLong(
                Objects.requireNonNull(environment.getProperty("app.socketIo.datasource.hikari.idleTimeout")))
        );
        dataSource.setPoolName(Objects.requireNonNull(environment.getProperty("app.socketIo.datasource.hikari.poolName")));
        dataSource.setDriverClassName(environment.getProperty("app.socketIo.datasource.hikari.driverClassName"));
        dataSource.setMaxLifetime(Long.parseLong(
                Objects.requireNonNull(environment.getProperty("app.socketIo.datasource.hikari.maxLifetime")))
        );
        dataSource.setMaximumPoolSize(Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("app.socketIo.datasource.hikari.maximumPoolSize")))
        );
        dataSource.setMinimumIdle(Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("app.socketIo.datasource.hikari.minimumIdle")))
        );

        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean socketIoEntityManagerFactory(
            final EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            final @Qualifier("socketIoDataSource") DataSource dataSource) {

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("app.socketIo.datasource.jpa.hibernate.ddlAuto"));
        properties.put("hibernate.show_sql", environment.getProperty("app.socketIo.datasource.jpa.showSql"));
        properties.put("hibernate.dialect", environment.getProperty("app.socketIo.datasource.jpa.dialect"));
        properties.put("hibernate.format_sql", environment.getProperty("app.socketIo.datasource.jpa.hibernate.formatSql"));
        properties.put("hibernate.open-in-view", environment.getProperty("app.socketIo.datasource.jpa.openInView"));

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.personal.common.core.entity")
                .persistenceUnit("socketIoEntityManagerFactory")
                .properties(properties)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager socketIoTransactionManager(
            @Qualifier("socketIoEntityManagerFactory") EntityManagerFactory socketIoEntityManagerFactory) {
        return new JpaTransactionManager(socketIoEntityManagerFactory);
    }
}
