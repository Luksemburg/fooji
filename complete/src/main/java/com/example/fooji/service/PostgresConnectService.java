package com.example.fooji.service;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class PostgresConnectService {
    /*@Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;*/

    private String url = "jdbc:postgresql://localhost:5432/fooji";
    private String user = "postgres";
    private String password = "alpha";

    PostgresConnectService(){}

    public void connect() {
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to PostgreSQL");
            e.printStackTrace();
        }
    }
}

