package components;
import java.time.LocalDateTime;

public class Users {
	private String username; // username of the user
	private String pass; // password of the user
	private String pass_confirm; // password confirm of the user
	private String email; // email of the user
	private LocalDateTime creationData;
	private LocalDateTime modifiedData;
	private Boolean admin;
	
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
	 * @param admin true or false
	 */
	public Users(String username, String pass, String pass_confirm, String email, boolean admin) {
		super();
		this.username = username;
		this.pass = pass;
		this.pass_confirm = pass_confirm;
		this.email = email;
		this.creationData = LocalDateTime.now();
		this.modifiedData = LocalDateTime.now();
		this.admin = false;
	}
	
	/**	Create user without admin
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
		this.creationData = LocalDateTime.now();
		this.modifiedData = LocalDateTime.now();
	}
	
	/**	Create user for verification login
	 * @param username	user name of the user
	 * @param pass	password of the user
	 */
	public Users(String username, String pass) {
		super();
		this.username = username;
		this.pass = pass;
	}
	
	/**	Create user
	 * @param username	user name of the user
	 */
	public Users(String username) {
		super();
		this.username = username;
	}
	
	/**	Create user for admin windows
	 * @param username	user name of the user
	 * @param pass	password of the user
	 * @param admin true or false
	 */
	public Users(String username, String pass, boolean admin) {
		super();
		this.username = username;
		this.pass = pass;
		this.admin = admin;
	}
	
	/**	Create user for obteins admins
	 * @param username	user name of the admin
	 * @param pass	password of the admin
	 * @param email	admin email
	 */
	public Users(String username, String pass, String email) {
		super();
		this.username = username;
		this.pass = pass;
		this.email = email;
	}
	
	/**	Create user for obteins users
	 * @param username	user name of the user
	 * @param pass	password of the user
	 * @param email	email of the user
	 * @param admin true or false
	 */
	public Users(String username, String pass, String email, boolean admin) {
		super();
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.admin = admin;
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
	 * @param pass_confirm	String of the password confirm
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
	 * @param email	String of the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Get creation data
	 * @param creationData	LocalDateTime of creation data
	 */
	public LocalDateTime getCreationData() {
		return creationData;
	}
	/** Get modified data
	 * @param modifiedData	LocalDateTime of last modified
	 */
	public LocalDateTime getModifiedData() {
		return modifiedData;
	}
	/** Modified the modifiedData
	 * @param modifiedData	LocalDateTime of the modifiedData, when the user change the password.
	 */
	public void setModifiedData(LocalDateTime modifiedData) {
		this.modifiedData = LocalDateTime.now();
	}

	/** Get admin
	 * @param admin	Boolean if is admin
	 */
	public Boolean getAdmin() {
		return admin;
	}
	/** Modified admin
	 * @param admin	Boolean if is admin
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return username+"\t"+pass;
	}
	
}
