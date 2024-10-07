package window;

import java.awt.*;
import javax.swing.*;

public class WindowMain extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame wCurrent;
	
	private JPanel pCenter, pCenterLeft, pCenterRight, pNorth, pSouth, pEast, pWest;
	
	private JButton btn_login, btn_register;
	
	private JLabel lblNothText;
	
	public WindowMain() {
		super();
		
		/* WINDOW */
		wCurrent = this;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("UD Students - Main");
		setBounds(200, 200, 600, 400);
		setResizable(false);
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		/* PANELS */
		pCenter = new JPanel();
		pCenterLeft = new JPanel();
		pCenterRight = new JPanel();
		pNorth = new JPanel();
		pSouth = new JPanel();
		pEast = new JPanel();
		pWest = new JPanel();
		
		/* BUTTONS */
		btn_login = new JButton("Login");
		btn_register = new JButton("Register");
		
		/* LABELS */
		lblNothText = new JLabel("UD Students - CINEMA");
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		
		/* DEFINIR PANELES PRINCIPALES */
		getContentPane().add(pCenter, BorderLayout.CENTER);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pEast, BorderLayout.EAST);
		getContentPane().add(pWest, BorderLayout.WEST);
		
		/* AGREGAR ELEMENTOS A LOS PANELES */
		pCenterLeft.setLayout(new FlowLayout(FlowLayout.CENTER));
		pCenterRight.setLayout(new FlowLayout(FlowLayout.CENTER));
		pCenterLeft.add(btn_login);
		pCenterRight.add(btn_register);
		pCenter.add(pCenterLeft);
		pCenter.add(pCenterRight);
		pNorth.add(lblNothText);
		
		/* EVENTS */
		/* BTN_LOGIN
		 * Boton que presionas, oculta la ventana actual y posteriormente crea una nueva ventana de la clase WindowLogin
		 */
		btn_login.addActionListener(e -> {
			wCurrent.dispose();
			new WindowLogin(wCurrent);
		});
		
		/* BTN_REGISTER
		 * Boton que presionas, oculta la ventana actual y posteriormente crea una nueva ventana de la clase WindowRegister
		 */
		btn_register.addActionListener(e -> {
			wCurrent.dispose();
			new WindowRegister(wCurrent);
		});
		
		/* THREAD CREATE
		 * Rotacion de texto que pone UD Students - CINEMA
		 */
		Runnable r = new Runnable() {
		    @Override
		    public void run() {
		        int x = -lblNothText.getWidth();
		        while(true) {
		            x += 10;
		            if(x > pNorth.getWidth()) {
		                x = -lblNothText.getWidth();
		            }
		            lblNothText.setBounds(x, lblNothText.getY(), lblNothText.getWidth(), lblNothText.getHeight());
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
		
		/* VISIBILIDAD */
		setVisible(true);
	}
}