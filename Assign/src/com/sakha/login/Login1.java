package com.sakha.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.sakha.database.connect.ConnectionManager;
import com.sakha.register.Register;

public class Login1 {

	String userName;
	String password;
	Scanner s = new Scanner(System.in);

	public void getUser() throws ClassNotFoundException, SQLException {
		System.out.println("==============LOGIN==============");
		System.out.println("Please enter user name : ");
		userName = s.nextLine();
		System.out.println("Please enter passwoprd");
		password = s.nextLine();
		System.out.println("=================================");

		validate();

	}

	public void validate() throws ClassNotFoundException, SQLException {

		ConnectionManager c = new ConnectionManager();
		Connection con = c.getConnect();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from users");

		// ArrayList<String> al = new ArrayList<String>();

		if (rs.next()) {
			boolean flag = true;
			// al.add(rs.getString(1));
			while (flag) {
				if (userName.equals(rs.getString(1))) { // ----validation
														// without collection
					// if(al.contains(userName)){ //----validation with
					// collection
					if (password.equals(rs.getString(2))) {

						System.out.println("Valid User");
						System.out.println("Login Successfull !!!");

						// Show User details - code here
						// call showuserDetails();

						c.closeConnect();
						break;
					} else {
						System.out.println("Not a valid User!!!");
						break;
					}

				}
				flag = rs.next();

				if (!flag) {
					Login1 l = new Login1();
					l.newUser();
				}
			}
			c.closeConnect();

		} else {
			Login1 l = new Login1();
			l.newUser();
		}
	}

	public void newUser() throws ClassNotFoundException, SQLException {
		System.out.println("Not registered yet !!!\nLogin not possible\nPlease Register\n");
		System.out.println("Do you want to register ???\nIf yes then enter 'y' OR 'Y'");
		String ch = "N";
		ch = s.nextLine();
		// redirect to registration page
		if (ch == "y" || ch == "Y") {
			System.out.println("Thank You !!!");
			Register reg = new Register();
			reg.userDetails();
		} else {
			getUser();		
			 
		}
	}
}
