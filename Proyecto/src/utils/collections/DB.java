package utils.collections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import components.Users;

public class DB {
	private Connection con;
	private static boolean verified;
	private static boolean adminVerified = false;
	private List<Users> lUsers;
	private List<Users> lUsersAdmin;
	
	/**
	 * Connect to the database
	 */
	public void connectJDBC(String nombreBD) {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Se ha podido cargar driver de la DB");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			System.out.println("Conectado a la db correctamente.");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Disconnect from the database
	 */
	public void disconnectJDBC() {
		if (con != null) {
			try {
				con.close();
				System.out.println("Desconectado de la DB correctamente.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void crearTablas() {
		String sql = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, pass TEXT NOT NULL, pass_confirm TEXT NOT NULL, email TEXT NOT NULL, creationData TEXT DEFAULT (datetime('now')), modifiedData TEXT DEFAULT (datetime('now')), admin BOOLEAN NOT NULL);";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verify if user is register
	 * @param u_id			username of the JTextField
	 * @param passw			password of the JPasswordField
	 * @return 
	 * @return verified 	true(if it is regiter & username&&passw is the same), false(not register || username||passw isnt same)
	 */
	public boolean verificationUser(String u_id, String passw) {
		verified = false;
		lUsers = obtainUsers();
		for (Users u : lUsers) {
			if(u.getUsername().equals(u_id) && u.getPass().equals(passw)) {
				verified = true;
				break;
			}
		}
		return verified;
	}
	
	public Users userToVerify(String u_id, String passw) {
		lUsers = obtainUsers();
		Users us = null;
		for (Users u : lUsers) {
			if(u.getUsername().equals(u_id) && u.getPass().equals(passw)) {
				us = u;
				break;
			}
		}
		return us;
	}
	
	/**
	 *  Verify if the user is admin
	 * @param u_id				username of the user to verify if it is admin
	 * @return adminVerified	true (is admin), false (isnt admin)
	 */
	
	public boolean userIsAdmin(String u_id) {
		lUsersAdmin = obtainUsers();
		for (Users u : lUsers) {
			if(u.getUsername().equals(u_id)) {
				adminVerified = true;
				break;
			}
		}
		return adminVerified;
	}
	
	public List<Users> obtainUsers(){
		String sql;
		lUsers = new ArrayList<>();
		
		try {
			Statement st = con.createStatement();
			sql = "SELECT * FROM users";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				//Obtenemos la información a la que hace referencia rs
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
				boolean admin = rs.getBoolean("admin");
				
				Users a = new Users(username, pass, email, admin);
				lUsers.add(a);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lUsers;
	}
		
	public List<Users> obtainUsersAdmin(){
		String sql;
		lUsersAdmin = new ArrayList<>();
		
		try {
			Statement st = con.createStatement();
			sql = "SELECT * FROM users_admin";
			ResultSet rs = st.executeQuery(sql);

			while(rs.next()) {
				//Obtenemos la información a la que hace referencia rs
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
				
				Users a = new Users(username, pass, email);
				lUsersAdmin.add(a);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lUsersAdmin;
	}
	
	public Boolean registerUsers(String username, String pass, String pass_confirm, String email) {
		Boolean register = true;
		try {
			String checkUsernameSql = "SELECT COUNT(*) FROM users WHERE username = ?";
	        PreparedStatement checkUsernameStmt = con.prepareStatement(checkUsernameSql);
	        checkUsernameStmt.setString(1, username);
	        ResultSet rs = checkUsernameStmt.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            System.out.println("El nombre de usuario ya existe.");
	            register = false;
	        }
	        
	        String checkEmailSql = "SELECT COUNT(*) FROM users WHERE email = ?";
            PreparedStatement checkEmailStmt = con.prepareStatement(checkEmailSql);
            checkEmailStmt.setString(1, email);
            rs = checkEmailStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("El correo electrónico ya está registrado.");
                register = false;
            }
	        
	        if (register) {
	        	String sql = "INSERT INTO users ('username', 'pass', 'pass_confirm', 'email', 'admin') VALUES ('" + username + "', '" + pass + "', '" + pass_confirm + "', '" + email + "', FALSE)";
				try(PreparedStatement ps = con.prepareStatement(sql);) {
					ps.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        checkUsernameStmt.close();
	        checkEmailStmt.close();
		}catch (SQLException e) {
            e.printStackTrace();
		}
		return register;
	}
	
	public Boolean editUserPassword(String username, String pass) {
		Boolean edit = true;
		try {
			String checkUserSql = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement checkUserStmt = con.prepareStatement(checkUserSql);
            checkUserStmt.setString(1, username);
            ResultSet rs = checkUserStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("El usuario no existe.");
                edit = false;
            }
            
            if(edit) {
            	String sql = "UPDATE users SET pass = '" + pass + "' WHERE username = '" + username + "'";
            	String sql2 = "UPDATE users SET pass_confirm = '" + pass + "' WHERE username = '" + username + "'";
    			try(PreparedStatement ps = con.prepareStatement(sql);PreparedStatement ps2 = con.prepareStatement(sql2);) {
    				ps.execute();
    				ps2.execute();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
            }
            rs.close();
            checkUserStmt.close();
		} catch (SQLException e) {
            e.printStackTrace();
            edit = false;
        }
		
		return edit;
		
	}
}
