package window;
import java.awt.*;
import javax.swing.*;

public class WindowRegister extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	
	private JPanel pNorth, panelArriba, panelCenter, panel1Center, panel2Center, panelSouth;
	
	private JButton exitBotton, registerBotton;
	
	private JLabel lblNorthText, userLabel, emailLabel, passwordLabel, passwordComfirmationLabel;
	
	private JTextField userText, emailText, passwordText, passwordConfirmationText;
	
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
		
		/* BUTTONS MODIFICATIONS */
		registerBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		exitBotton.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
		
		/* TEXT FIELD */
		userText = new JTextField();
		emailText = new JTextField();
		passwordText = new JTextField();
		passwordConfirmationText = new JTextField();
		
		/* LABELS */
		userLabel = new JLabel("Username");
		emailLabel = new JLabel("Email");
		passwordLabel = new JLabel("Password");
		passwordComfirmationLabel = new JLabel("Repeat password");
		lblNorthText = new JLabel("UD Students - Register");
		
		/* LABELS MODIFICATIONS */
		userLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    userLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    emailLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    passwordLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    passwordComfirmationLabel.setFont(new Font("Calibri", Font.CENTER_BASELINE, 30));
	    passwordComfirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNorthText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNorthText.setForeground(Color.CYAN);
		
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
	    
		/* EVENTS */
	    /* BTN_EXIT
		 * Boton que presionas, oculta la ventana actual y posteriormente enseña la ventana anterior.
		 */
	    exitBotton.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
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
}
