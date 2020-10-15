package com.sistec.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class StudentDb {

    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String pwd = "iniwhsa";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static Connection conn = null;

    static {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, pwd);
        } catch (Exception e) {
            System.out.println("Connection failed:" + e);
        }
    }

    public static Connection getStudentDb() {
        return conn;
    }

    public static void main(String[] args) {
        System.out.println(getStudentDb());
    }
}
