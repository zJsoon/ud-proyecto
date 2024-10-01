package components;

public class Users {
	private String username; // username of the user
	private String pass; // password of the user
	
	/** Default constructor
	 * 
	 */
	public Users() {
		super();
	}
	
	/**	Create new user
	 * @param username	user name of the user
	 * @param pass	password of the user
	 */
	public Users(String username, String pass) {
		super();
		this.username = username;
		this.pass = pass;
	}
	/** Get the user name 
	 * @return String of the username gora 
	 */
	public String getUsername() {
		return username;
	}
	
	/** Enter the new user name
	 * @param username	String of the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** Get the password 
	 * @return	String of the password
	 */
	public String getPass() {
		return pass;
	}
	
	/** Enter the new password
	 * @param username	String of the new password
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return username+"\t"+pass;
	}
	
	// comprobacion de conexion
}
