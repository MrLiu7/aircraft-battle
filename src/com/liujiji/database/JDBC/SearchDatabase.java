package com.liujiji.database.JDBC;

import java.sql.*;

/**
 * 数据库查询
 *
 * @author 柳继纪
 * @date 13/5/2022
 * @apiNote return boolean
 */
public class SearchDatabase {
    public static boolean searchUser(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://8.130.8.244:3306/account", "xiaoliu", "525658");
            statement = connection.createStatement();
            //定义SQL语句 "SELECT * FROM user WHERE id=" + "'" + userID + "'" + "AND password=" + "'" + userPassword + "'";
            //获取结果的方法
            set = statement.executeQuery(sql);

            //游标下一一行，默认指向第一行
            return set.next();
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
            if (set != null) {
                try {
                    set.close();
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }
}