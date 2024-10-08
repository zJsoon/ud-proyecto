package window;

import java.awt.*;
import javax.swing.*;

import window.logged.WindowLogged;

public class WindowLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	private JTextField userText;
	private JPasswordField passwordText;
	@SuppressWarnings("deprecation")
	public WindowLogin(JFrame wPrevious) {
		super();
		
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		setBounds(200,200,600,400);
		
		setTitle("UD Students - Main");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		// Creacion de JLabel y JText 
		JLabel userLabel = new JLabel("Username");
	    userLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    userText = new JTextField();
	    
	    JLabel passwordLabel = new JLabel("Password");
	    passwordLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    passwordText = new JPasswordField();
	    
	    // Creacion de Botones
	    JButton loginBotton = new JButton("Login");
	    loginBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    JButton exitBotton = new JButton("Exit");
	    exitBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		
	    // Creacion de paneles
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(2,1));
	    
	    JPanel panelNorth = new JPanel();
	    panelNorth.setLayout(new GridLayout(2,2));
	    
	    panelNorth.add(userLabel);
	    panelNorth.add(userText);
	    panelNorth.add(passwordLabel);
	    panelNorth.add(passwordText);
	    
	    JPanel panelSouth = new JPanel();
		
	    panelSouth.add(loginBotton);
	    panelSouth.add(exitBotton);
	    
	    panel.add(panelNorth, BorderLayout.NORTH);
	    panel.add(panelSouth, BorderLayout.SOUTH);
	    
	    
	    JTextField titleText = new JTextField("UD Student: Login");
	    titleText.setEditable(false);
	    titleText.setFont(new Font("Calibri", Font.CENTER_BASELINE, 50));
	    titleText.setBackground(Color.GRAY);
	    
	    add(titleText,BorderLayout.NORTH);
	    add(panel,BorderLayout.CENTER);
		add(panelSouth, BorderLayout.SOUTH);
	    
		exitBotton.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
		});
		
		loginBotton.addActionListener(e -> {
			// Lógica del BOTÓN btnLogin
			if(userText.getText().isBlank()) {
				System.out.println("Rellene el apartado del usuario.");
				JOptionPane.showMessageDialog(null, "Rellene el apartado del usuario.");
			} else if(passwordText.getText().isBlank()) {
				System.out.println("Rellene el apartado del password.");					
				JOptionPane.showMessageDialog(null, "Rellene el apartado del password.");
			} else if (userText.getText().isBlank() && passwordText.getText().isBlank()) {
				System.out.println("Rellene los apartados usuario y password.");					
				JOptionPane.showMessageDialog(null, "Rellene los apartados usuario y password.");					
			} else if(userText.getText().equals("123") && passwordText.getText().equals("123")) {
				System.out.println("Has iniciado sesión correctamente.");
				JOptionPane.showMessageDialog(null, "Has iniciado sesión correctamente.");
				vaciarCampos(1);
				wCurrent.dispose(); // Cierro ventana actual
				new WindowLogged(wCurrent); // Abrimos la ventana2 indicando que su ventana anterior es (this).
			} else if(!userText.getText().equals("123")) {
				System.out.println("Usuario incorrecto.");					
				JOptionPane.showMessageDialog(null, "Usuario incorrecto.");
				vaciarCampos(2);
			} else if(!passwordText.getText().equals("123")) {
				System.out.println("Contraseña incorrecta.");
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
				vaciarCampos(3);
		}
		});
	    
	    setVisible(true);
	
	}
	public void vaciarCampos (int i) {
		if (i == 1) {
			userText.setText("");
			passwordText.setText("");
		} else if ( i == 2 ) {
			userText.setText("");
		} else if (i == 3 ) {
			passwordText.setText("");
		}
	}
}
