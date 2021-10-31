package com.koreait.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtils {
    public static Connection getCon() throws Exception {
        final String URL = "jdbc:mysql://localhost:3306/test";
        final String USERNAME = "root";
        final String PASSWORD = "ansghkwo12";
        Class.forName("com.mysql.cj.jdbc.Driver");//컴파일러에게 내가 쓸 드라이브를 알려주는것
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("접속 성공!");
        return con;
    }

    public static void close(Connection con, PreparedStatement ps) {
        close(con, ps, null);
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        if(rs != null) {
            try { rs.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        if(ps != null) {
            try { ps.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        if(con != null) {
            try { con.close(); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}