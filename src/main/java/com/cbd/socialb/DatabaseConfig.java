package com.cbd.socialb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.DriverManager;

@Configuration
public class DatabaseConfig {

    @Value("${SPRING_DATASOURCE_URL}")
    private String dbUrl;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    private String dbUsername;

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    private String dbPassword;


}
