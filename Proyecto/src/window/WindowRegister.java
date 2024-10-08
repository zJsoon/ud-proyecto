package window;
import java.awt.Font;

import javax.swing.*;

public class WindowRegister extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame wCurrent, wPrevious;
	public WindowRegister(JFrame wPrevious) {
		super();
		
		setBounds(200,200,600,400);
		setTitle("UD Students - Register");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		JLabel userLabel = new JLabel("Username");
	    userLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JTextField userText = new JTextField();
	    
	    
	    JLabel emailLabel = new JLabel("Email");
	    emailLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JTextField emailText = new JTextField();
	    
	    
	    JLabel passwordLabel = new JLabel("Password");
	    passwordLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JTextField passwordText = new JTextField();
	    
	    JLabel passwordComfirmationLabel = new JLabel("Repeat password");
	    passwordComfirmationLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordComfirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JTextField passwordConfirmationText = new JTextField();
		
		setVisible(true);
	}
}