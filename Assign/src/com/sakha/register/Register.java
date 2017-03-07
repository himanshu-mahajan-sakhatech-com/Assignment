package com.sakha.register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.sakha.database.connect.ConnectionManager;
import com.sakha.login.Login1;

public class Register {

	Scanner s = new Scanner(System.in);

	String userName;
	String pass;
	String firstNmae;
	String lastName;
	String mobileNumber;
	String city;

	public void userDetails() throws SQLException, ClassNotFoundException {
		System.out.println("\n\n\n\n");
		System.out.println("=================================");
		System.out.println("=======Registration Form=========");
		System.out.println("=================================");
		System.out.println("Pleasee enter user name");
		userName = s.nextLine();
		System.out.println("Please enter password");
		pass = s.nextLine();
		System.out.println("Pleasee enter First name");
		firstNmae = s.nextLine();
		System.out.println("Pleasee enter Last name");
		lastName = s.nextLine();
		System.out.println("Pleasee enter mobile number");
		mobileNumber = s.nextLine();
		System.out.println("Pleasee enter city");
		city = s.nextLine();
		System.out.println("=================================");
		insertRecord(userName, pass, firstNmae, lastName, mobileNumber, city);

	}

	public void insertRecord(String uName, String pass, String fName, String lName, String mob, String city)
			throws SQLException, ClassNotFoundException {

		ConnectionManager c = new ConnectionManager();
		Connection con = c.getConnect();
		
		String query1 = "insert into user_detail1 values(?,?,?,?,?)";
		PreparedStatement ps1= con.prepareStatement(query1);
		ps1.setString(1, uName);
		ps1.setString(2, fName);
		ps1.setString(3, lName);
		ps1.setString(4, mob);
		ps1.setString(5, city);
		ps1.executeUpdate();
		
		String query2 = "insert into users values(?,?)";
		PreparedStatement ps2 = con.prepareStatement(query2);
		ps2.setString(1, uName);
		ps2.setString(2, pass);
		ps2.executeQuery();
		
		System.out.println("=================================");
		System.out.println("Registration SuccessFull");
		
		String query3 = "select * from users where pass ='"+pass+"'";		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query3);
		rs.next();
		System.out.println("Your Login id is : "+ rs.getString(1));
		System.out.println("Please use this id to login to your Account");
		System.out.println("=================================");
		
		
		System.out.println("\n\n\nPlease Login to your account!!!");
		
		Login1 l = new Login1();
		l.getUser();
		
		c.closeConnect();
	}

}
