package window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import window.logged.WindowLogged;

public class WindowLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	
	private JPanel pNorth, panel, panelCenter, panelSouth;
	
	private JButton loginBotton, exitBotton;
	
	private JTextField userText;
	
	private JPasswordField passwordText;
	
	private JLabel lblNorthText, lblResetPassword, userLabel, passwordLabel;
	@SuppressWarnings("deprecation")
	public WindowLogin(JFrame wPrevious) {
		super();
		/* WINDOW */
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("UD Students - Login");
		setBounds(200, 200, 600, 400);
		setResizable(false);
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		/* PANELS */
		panel = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		pNorth = new JPanel();
		
		/* PANELS MODIFICATIONS */
		panel.setLayout(new GridLayout(2,1));
		panelCenter.setLayout(new GridLayout(2,2));
		
		/* BUTTONS */
		loginBotton = new JButton("Login");
		exitBotton = new JButton("Exit");
		
		/* BUTTONS MODIFICATIONS */
		loginBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		exitBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		
		/* TEXT FIELD */
		userText = new JTextField();
		passwordText = new JPasswordField();
		
		/* LABELS */
		userLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		lblResetPassword = new JLabel("Reset Password");
		lblNorthText = new JLabel("UD Students - Login");
		
		/* LABELS MODIFICATIONS */
		userLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    passwordLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblResetPassword.setHorizontalAlignment(SwingConstants.CENTER);
	    lblResetPassword.setVerticalAlignment(SwingConstants.CENTER);
	    lblNorthText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNorthText.setForeground(Color.CYAN);
	    
		/* DEFINIR PANELES PRINCIPALES */
	    getContentPane().add(pNorth, BorderLayout.NORTH);
	    getContentPane().add(panel, BorderLayout.CENTER);
	    getContentPane().add(panelSouth, BorderLayout.SOUTH);
	    
		/* AGREGAR ELEMENTOS A LOS PANELES */
	    panelCenter.add(userLabel);
	    panelCenter.add(userText);
	    panelCenter.add(passwordLabel);
	    panelCenter.add(passwordText);
	    panelSouth.add(loginBotton);
	    panelSouth.add(exitBotton);
	    pNorth.add(lblNorthText);
	    panel.add(panelCenter, BorderLayout.CENTER);
	    panel.add(lblResetPassword);
	    
		/* EVENTS */
	    /* BTN_EXIT
		 * Boton que presionas, oculta la ventana actual y posteriormente enseña la ventana anterior.
		 */
	    exitBotton.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
		});
	    
	    /* loginBotton
		 * Boton que presionas, oculta la ventana actual y posteriormente comprueba que todos los campos esten correctos y en caso de que asi sea inicia sesión.
		 */
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
		/* lblResetPassword
		 * Texto a presionar que en caso de hacerlo oculta la ventana actual y posteriormente crea una ventana donde podras cambiar la contraseña.
		 */
		lblResetPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				wCurrent.dispose();
				new WindowResetPassword(wCurrent);
			}
		});
		/* THREAD CREATE
		 * Rotacion de texto que pone UD Students - Login
		 */
		Runnable r = new Runnable() {
		    @Override
		    public void run() {
		        int x = -lblNorthText.getWidth();
		        while(true) {
		            x += 10;
		            if(x > pNorth.getWidth()) {
		                x = -lblNorthText.getWidth();
		            }
		            lblNorthText.setBounds(x, lblNorthText.getY(), lblNorthText.getWidth(), lblNorthText.getHeight());
		            try {
		                Thread.sleep(50);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		};
		Thread t = new Thread(r);
		t.start();
		
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