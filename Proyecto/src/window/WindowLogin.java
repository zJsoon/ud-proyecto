package window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;

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
		
		
		// Creacion de JLabel y JText 
		JLabel userLabel = new JLabel("Username");
	    userLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JTextField userText = new JTextField();
	    
	    JLabel passwordLabel = new JLabel("Password");
	    passwordLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JTextField passwordText = new JTextField();
	    
	    
	    // Creacion de Botones
	    JButton loginBotton = new JButton("Login");
	    loginBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    JButton exitBotton = new JButton("Exit");
	    exitBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    JButton registerBotton = new JButton("Register");
	    registerBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		
	    // Creacion de paneles
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(2,1));
	    
	    JPanel panelNorth = new JPanel();
	    panelNorth.setLayout(new GridLayout(2,2));
	    
	    panelNorth.add(userLabel);
	    panelNorth.add(userText);
	    panelNorth.add(passwordLabel);
	    panelNorth.add(passwordText);
	    
	    JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER,50,150));
		
	    panelSouth.add(loginBotton);
	    panelSouth.add(exitBotton);
	    panelSouth.add(registerBotton);
	    
	    panel.add(panelNorth, BorderLayout.NORTH);
	    panel.add(panelSouth, BorderLayout.SOUTH);
	    
	    
	    JTextField titleText = new JTextField("UD Student: Login");
	    titleText.setEditable(false);
	    titleText.setFont(new Font("Calibri", Font.CENTER_BASELINE, 50));
	    titleText.setBackground(Color.GRAY);
	    
	    add(titleText,BorderLayout.NORTH);
	    add(panel,BorderLayout.CENTER);
		
	    
	    setVisible(true);
	
	}
}
