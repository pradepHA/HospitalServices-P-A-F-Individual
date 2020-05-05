package com;

import java.sql.*;


import com.mysql.cj.xdevapi.Statement;

public class Hospital {
	
	
	private Connection connect() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb?serverTimezone=UTC", "root", "");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;

    }
	
	