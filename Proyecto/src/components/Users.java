package components;
import java.time.LocalDateTime;

public class Users {
	private String username; // username of the user
	private String pass; // password of the user
	private String pass_confirm; // password confirm of the user
	private String email; // email of the user
	private LocalDateTime creationData;
	private LocalDateTime modifiedData;
	
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
		this.creationData = LocalDateTime.now();
		this.modifiedData = LocalDateTime.now();
	}
	
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

	@Override
	public String toString() {
		return username+"\t"+pass;
	}
	
}
