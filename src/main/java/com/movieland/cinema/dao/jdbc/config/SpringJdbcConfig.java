package com.movieland.cinema.dao.jdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ComponentScan("com.movieland.cinema.dao.jdbc")
public class SpringJdbcConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.saltedPassword}")
    private String saltedPassword;

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public String cypher() {
        return new String(Base64.decodeBase64(saltedPassword.getBytes()));
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl + secret);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(cypher());
        return dataSource;
    }
}