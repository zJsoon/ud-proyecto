package window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
		
		
		// Creacion de JLabel y JTextField
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
	    
	    
	    JButton loginBotton = new JButton("Login");
	    loginBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    JButton exitBotton = new JButton("Exit");
	    exitBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    JButton registerBotton = new JButton("Register");
	    registerBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    
	    
	    JTextField titleText = new JTextField("UD Student: Student Register");
	    titleText.setEditable(false);
	    titleText.setFont(new Font("Calibri", Font.CENTER_BASELINE, 50));
	    titleText.setBackground(Color.GRAY);
	    
	    JPanel panelNorth = new JPanel();
	    panelNorth.setLayout(new GridLayout(2,1));
	    
	    panelNorth.add(userLabel);
	    panelNorth.add(emailLabel);
	    panelNorth.add(userText);
	    panelNorth.add(emailText);
	    
	    JPanel panelCenter = new JPanel();
	    panelCenter.setLayout(new GridLayout(2,1));
	   
	    
	    JPanel panel1Center = new JPanel();
	    panel1Center.setLayout(new GridLayout(2,1));
	    panel1Center.add(passwordLabel);
	    panel1Center.add(passwordText);
	    
	    JPanel panel2Center = new JPanel();
	    panel2Center.setLayout(new GridLayout(2,1));
	    panel2Center.add(passwordComfirmationLabel);
	    panel2Center.add(passwordConfirmationText);
	    
	    panelCenter.add(panel1Center,BorderLayout.NORTH);
	    panelCenter.add(panel2Center,BorderLayout.SOUTH);
	    
	    JPanel panelSouth = new JPanel();
	    panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER,50,150));
	    
	    panelSouth.add(registerBotton);
	    panelSouth.add(exitBotton);
	    panelSouth.add(loginBotton);
	    
	    JPanel panelRegister = new JPanel();
	    panelRegister.setLayout(new GridLayout(3,1));
	    panelRegister.add(panelNorth);
	    panelRegister.add(panelCenter);
	    panelRegister.add(panelSouth);
	    
	    
	    add(titleText,BorderLayout.NORTH);
	    this.add(panelRegister,BorderLayout.CENTER);
		
		setVisible(true);
	}
}