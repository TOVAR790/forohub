package com.ecodeup.jdbc;

import org.hibernate.annotations.processing.SQL;

import java.sql.*;

public class ConJDBC {
    public static void main(String[] args) {
        String url = "jdbc: mysql://localhost:3306/forohub?serverTimezone=UTC";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SLECT * FROM Topic");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " | " + resultSet.getString(" author") + " | " + resultSet.getString("curso") + " | " + resultSet.getString(" Respuesta"));
            }
            connection.close();
            statement.close();
            resultSet.close();
            }catch(SQLException e){
            e.printStackTrace();
            }
        }
    }







