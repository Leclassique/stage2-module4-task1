package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    // Write your code here!
    @Override
    public Connection createConnection(){
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("h2database.properties");
        try {
            properties.load(inputStream);
            try {
                Class.forName(properties.getProperty("jdbc_driver"));
                return DriverManager.getConnection(
                        properties.getProperty("db_url")
                        , properties.getProperty("user")
                        , properties.getProperty("password"));
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}