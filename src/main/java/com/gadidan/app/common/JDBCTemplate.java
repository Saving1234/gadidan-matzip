package com.gadidan.app.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/resources/db.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            return DriverManager.getConnection(url, prop);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new DatabaseConnectionException(
                    "데이터베이스 연결에 실패했습니다.", e);
        }
    }

    public static void close(Connection con) {

        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {

        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(ResultSet rset) {

        try {
            if (rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
