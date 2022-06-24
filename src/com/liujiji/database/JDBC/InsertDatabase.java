package com.liujiji.database.JDBC;

import java.sql.*;

public class InsertDatabase {
    public static void insert(String sql) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://8.130.8.244:3306/account", "xiaoliu", "525658");
            statement = connection.createStatement();
            //定义SQL语句
            //插入数据
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
