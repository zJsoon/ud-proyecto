package window.verification;
import java.awt.*;
import javax.swing.*;
import utils.collections.DB;

public class WindowRegister extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;

	

	
	private JPanel pNorth, panelArriba, panelCenter, panel1Center, panel2Center, panelSouth;
	
	private JButton exitBotton, registerBotton;
	
	private JLabel lblNorthText, userLabel, emailLabel, passwordLabel, passwordComfirmationLabel;
	
	private JTextField userText, emailText;
	private JPasswordField passwordText, passwordConfirmationText;
	
	@SuppressWarnings("deprecation")
	public WindowRegister(JFrame wPrevious) {

		super();
		
		/* WINDOW */
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("UD Students - Register");
		setBounds(200, 200, 600, 400);
		setResizable(false);
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		

		
		// Creacion de JLabel y JTextField
		userLabel = new JLabel("Username");

		/* PANELS */
		panelArriba = new JPanel();
		panelCenter = new JPanel();
		panel1Center = new JPanel();
		panel2Center = new JPanel();
		panelSouth = new JPanel();
		pNorth = new JPanel();
		
		/* PANELS MODIFICATIONS */
		panelArriba.setLayout(new GridLayout(2,1));
		panelCenter.setLayout(new GridLayout(3,1));
		panel1Center.setLayout(new GridLayout(2,1));
		panel2Center.setLayout(new GridLayout(2,1));
		
		/* BUTTONS */
		registerBotton = new JButton("Register");
		exitBotton = new JButton("Exit");
	
		
		/* TEXT FIELD */
		userText = new JTextField();
		emailText = new JTextField();
		passwordText = new JPasswordField();
		passwordConfirmationText = new JPasswordField();
		
		/* LABELS */
		userLabel = new JLabel("Username");
		emailLabel = new JLabel("Email");
		passwordLabel = new JLabel("Password");
		passwordComfirmationLabel = new JLabel("Repeat password");
		lblNorthText = new JLabel("UD Students - Register");
		
		/* LABELS MODIFICATIONS */

	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    passwordComfirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNorthText.setForeground(Color.CYAN);
		lblNorthText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		
		/* DEFINIR PANELES PRINCIPALES */
	    getContentPane().add(panelCenter, BorderLayout.CENTER);
	    getContentPane().add(panelSouth, BorderLayout.SOUTH);
	    getContentPane().add(pNorth, BorderLayout.NORTH);
	    
		/* AGREGAR ELEMENTOS A LOS PANELES */
	    panelArriba.add(userLabel);
	    panelArriba.add(emailLabel);
	    panelArriba.add(userText);
	    panelArriba.add(emailText);
	    panel1Center.add(passwordLabel);
	    panel1Center.add(passwordText);
	    panel2Center.add(passwordComfirmationLabel);
	    panel2Center.add(passwordConfirmationText);
	    panelCenter.add(panelArriba, BorderLayout.CENTER);
	    panelCenter.add(panel1Center,BorderLayout.NORTH);
	    panelCenter.add(panel2Center,BorderLayout.SOUTH);
	    panelSouth.add(registerBotton);
	    panelSouth.add(exitBotton);
	    pNorth.add(lblNorthText);
	    
	    
	    /* DB */
	    DB db_u = new DB();
	    db_u.connectJDBC("resources\\db\\db_proyecto.db");
	    
		/* EVENTS */
	    /* BTN_EXIT
		 * Boton que presionas, oculta la ventana actual y posteriormente enseña la ventana anterior.
		 */
	    exitBotton.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
		});
	    
	    registerBotton.addActionListener(e ->{
	    	if(passwordText.getText().equals(passwordConfirmationText.getText())) {
	    		boolean register =  db_u.registerUsers(userText.getText(), passwordText.getText(), passwordConfirmationText.getText(), emailText.getText());
	    		if(register) {
	    			System.out.println("Te has registrado correctamente.");
					JOptionPane.showMessageDialog(null, "Has sido registrado correctamente.");
					vaciarCampos(3);
					wCurrent.dispose();
					new WindowLogin(wCurrent); 
	    		}else {
	    			System.out.println("No se ha podido registrar, ya existia un usuario con mismo username o email.");
					JOptionPane.showMessageDialog(null, "No se ha podido registrar correctamente, nombre de usuario o email registrados.");
					vaciarCampos(2);
	    		}
	    		
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null, "Confirmación de contraseña incorrecto pruebelo de nuevo.");
	    		vaciarCampos(1);
	    		System.out.println("Las contraseñas no coinciden.");
	    	}
	    });
	    
		/* THREAD CREATE
		 * Rotacion de texto que pone UD Students - CINEMA
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
			passwordText.setText("");
			passwordConfirmationText.setText("");
		}else if (i == 2) {
			userText.setText("");
			emailText.setText("");
		}else if(i == 3) {
			userText.setText("");
			passwordText.setText("");
			passwordConfirmationText.setText("");
			emailText.setText("");
		}
	}
}
