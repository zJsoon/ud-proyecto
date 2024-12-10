package utils.collections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import components.Users;

public class ConnectionDB {
	private Connection con;
	private static boolean verified = false;
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
			System.out.println("Se ha podido cargar el driver de la DB");
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
	
	public void crearAdmin() {
		String sql = "INSERT INTO users VALUES (0, )";
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
		lUsers = obtainUsers();
		for (Users u : lUsers) {
			if(u.getUsername().equals(u_id) && u.getPass().equals(passw)) {
				verified = true;
				break;
			}
		}
		return verified;
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
			sql = "SELECT username, pass FROM users";
			ResultSet rs = st.executeQuery(sql);

			while(rs.next()) {
				//Obtenemos la información a la que hace referencia rs
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				
				Users a = new Users(username, pass);
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
				
				Users a = new Users(username);
				lUsersAdmin.add(a);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lUsersAdmin;
	}
}
