package window;
import java.awt.Font;

import javax.swing.*;

public class WindowLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame wCurrent, wPrevious;
	public WindowLogin(JFrame wPrevious) {
		super();
		
		setBounds(200,200,600,400);
		
		setTitle("UD Students - Main");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		JLabel userLabel = new JLabel("Username");
	    userLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JTextField userText = new JTextField();
	    
	    JLabel passwordLabel = new JLabel("Password");
	    passwordLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    
		
		
		setVisible(true);
	}
}
