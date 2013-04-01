package com.psychobit.hardcore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;

import com.googlecode.flyway.core.Flyway;

public abstract class DatabaseTest
{
    protected Connection dbConnection;
    protected String hostname;
    protected String database;
    protected String username;
    protected String password;

    @Before
    public void setUp() throws SQLException, FileNotFoundException, IOException
    {
        Properties properties = new Properties();
        properties.load(new FileInputStream("test.properties"));
        
        hostname = properties.getProperty("hostname");
        database = properties.getProperty("database");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        String dataSource = "jdbc:mysql://" + hostname + "/" + database;
    
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource, username, password);
        flyway.clean();
        flyway.migrate();
        
        dbConnection = DriverManager.getConnection(dataSource, username, password );
    }

    @After
    public void tearDown() throws SQLException
    {
        dbConnection.close();
    }
}