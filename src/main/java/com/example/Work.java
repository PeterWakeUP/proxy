package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Work {

    //@Affair
    public void doWork() {
        String url = "jdbc:mysql://127.0.0.1:3306/zz_my?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "admin";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();

            statement1.executeUpdate("INSERT INTO `student`(`id`,`name`, `score`) VALUES ('10','john10', '9010')");
            statement2.executeUpdate("INSERT INTO `student`(`name`, `score`) VALUES ('jack1', '881')");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
