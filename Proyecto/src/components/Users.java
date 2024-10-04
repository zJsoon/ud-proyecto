package components;

public class Users {
	private String username; // username of the user
	private String pass; // password of the user
	private String pass_confirm; // password confirm of the user
	private String email; // email of the user
	
	/** Default constructor
	 * 
	 */
	public Users() {
		super();
	}
	
	/**	Create new user
	 * @param username	user name of the user
	 * @param pass	password of the user
	 * @param pass_confirm	password confirm of the user
	 * @param email	email of the user
	 */
	public Users(String username, String pass, String pass_confirm, String email) {
		super();
		this.username = username;
		this.pass = pass;
		this.pass_confirm = pass_confirm;
		this.email = email;
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
	
	/** Get the password confirm
	 * @return	String of the password
	 */
	public String getPass_confirm() {
		return pass_confirm;
	}
	
	/** Enter the password confirm
	 * @param username	String of the password confirm
	 */
	public void setPass_confirm(String pass_confirm) {
		this.pass_confirm = pass_confirm;
	}
	
	/** Get the email
	 * @return	String of the email
	 */
	public String getEmail() {
		return email;
	}

	/** Enter the email
	 * @param username	String of the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return username+"\t"+pass;
	}
	
}
