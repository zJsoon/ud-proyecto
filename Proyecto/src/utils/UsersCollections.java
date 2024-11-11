package utils;

import java.sql.*;
import java.util.*;

import components.Users;
import db.ConnectionDB;

public class UsersCollections {
	private boolean verified = false;
	private boolean adminVerified = false;
	
	/**
	 * Verify if user is register
	 * @param u_id			username of the JTextField
	 * @param passw			password of the JPasswordField
	 * @return verified 	true(if it is regiter & username&&passw is the same), false(not register || username||passw isnt same)
	 */
	public boolean verificationUser(String u_id, String passw) {
		ConnectionDB.ConnectJDBC();
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:resources/db/users.db");
			System.out.println("He accedido correctamente.");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");		
					
			List<Users> usuarios = new ArrayList<Users>();
			
			while(rs.next()) {
				String user = rs.getString("user");
				String pass = rs.getString("password");
				
				usuarios.add(new Users(user, pass));
			}
			
			for (Users u : usuarios) {
				if(u.getUsername().equals(u_id) && u.getPass().equals(passw)) {
					verified = true;
					break;
				}
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return verified;
	}
	
	/**
	 * 
	 * @param u_id				username of the user to verify if it is admin
	 * @return adminVerified	true (is admin), false (isnt admin)
	 */
	public boolean userIsAdmin(String u_id) {
		ConnectionDB.ConnectJDBC();
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:resources/db/users_admin.db");
			System.out.println("He accedido correctamente.");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");		
					
			List<Users> usuarios = new ArrayList<Users>();
			
			while(rs.next()) {
				String user = rs.getString("user");
				String pass = rs.getString("password");
				boolean admin = rs.getBoolean("admin");
				
				usuarios.add(new Users(user, pass, admin));
			}
			
			for (Users u : usuarios) {
				if(u.getUsername().equals(u_id) && u.getAdmin() == true) {
					adminVerified = true;
					break;
				}
			}
			
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return adminVerified;
	}
}
