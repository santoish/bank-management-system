package com.sant.bankManagement.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection(){
        Properties properties = new Properties();
        try{
            InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");

            if(is == null){
                throw new Exception("db.properties File Not Found");
            }

            properties.load(is);

            String url = properties.getProperty("db.url");
            String userName = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            return DriverManager.getConnection(url,userName,password);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException("Database cannot Connected");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void closeConnection(Connection connection) throws SQLException{
        if(connection!= null){
            try{
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
