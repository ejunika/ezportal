package com.ez.jdbc.factory;

import java.sql.Connection;

public class JdbcConnectionFactory {
    
    private Connection connection;
    
    public JdbcConnectionFactory() {
        super();
    }
    
    public JdbcConnectionFactory configure() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
}
