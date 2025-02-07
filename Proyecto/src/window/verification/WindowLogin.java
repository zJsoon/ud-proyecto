package window.verification;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import components.Users;
import utils.collections.DB;
import window.WindowMain;
import window.logged.admin.WindowAdmin;
import window.logged.users.WindowLogged;

@SuppressWarnings("unused")
public class WindowLogin extends JFrame {
	private static final long serialVersionUID = 1L;
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
		
		
		/* TEXT FIELD */
		userText = new JTextField();
		passwordText = new JPasswordField();
		
		/* LABELS */
		userLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		lblResetPassword = new JLabel("Reset Password");
		lblNorthText = new JLabel("UD Students - Login");
		
		/* LABELS MODIFICATIONS */
		lblNorthText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblResetPassword.setHorizontalAlignment(SwingConstants.CENTER);
	    lblResetPassword.setVerticalAlignment(SwingConstants.CENTER);
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
	    
	    /* DB */
	    DB db_u = new DB();
	    db_u.connectJDBC("resources\\db\\db_proyecto.db");
	    
		/* EVENTS */
	    /* BTN_EXIT
		 * Boton que presionas, oculta la ventana actual y posteriormente enseña la ventana anterior.
		 */
	    exitBotton.addActionListener(e -> {
			wCurrent.disable();
			new WindowMain();
		});
	    
	    /* loginBotton
		 * Boton que presionas, oculta la ventana actual y posteriormente comprueba que todos los campos esten correctos y en caso de que asi sea inicia sesión.
		 */
		loginBotton.addActionListener(e -> {
			db_u.connectJDBC("resources\\db\\db_proyecto.db");
			Users u = db_u.userToVerify(userText.getText(), passwordText.getText());
			if(u != null){
				System.out.println("Has iniciado sesión correctamente.");
				JOptionPane.showMessageDialog(null, "Has iniciado sesión correctamente.");
				vaciarCampos(1);
				wCurrent.dispose(); // Cierro ventana actual
				new WindowLogged(wCurrent, u); // Abrimos la ventana2 indicando que su ventana anterior es (this).
			} else{
				JOptionPane.showMessageDialog(null, "Inicio de sesión incorrecto pruebelo de nuevo.");
				vaciarCampos(1);
			}
			db_u.disconnectJDBC();
		});
		/* lblResetPassword
		 * Texto a presionar que en caso de hacerlo oculta la ventana actual y posteriormente crea una ventana donde podras cambiar la contraseña.
		 */
		lblResetPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				wCurrent.dispose();
				new WindowResetPassword(wCurrent);
				db_u.disconnectJDBC();
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