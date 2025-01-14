package window.verification;

import java.awt.*;
import javax.swing.*;
import utils.collections.DB;

public class WindowResetPassword extends JFrame{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	
	private JPanel pCenter, pUsername, pPassword, pNorth, pSouth, pEast, pWest;
	
	private JLabel lblUsername, lblPass;
	
	private JButton btn_back, btn_submit;
	
	private JTextField txtUsername;
	
	private JPasswordField txtPassRecover;
	@SuppressWarnings("deprecation")
	public WindowResetPassword(JFrame wPrevious) {
		super();
		
		/* WINDOW */
		wCurrent = this;
		this.wPrevious = wPrevious;
		setTitle("UD Students - Reset Password");
		setBounds(200,200,600,400);
		setResizable(false);
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		/* PANELS */
		pCenter = new JPanel();
		pUsername = new JPanel();
		pPassword = new JPanel();
		pNorth = new JPanel();
		pSouth = new JPanel();
		pEast = new JPanel();
		pWest = new JPanel();
		pCenter.setLayout(new GridLayout(2,1));
		
		/* BUTTONS */
		btn_back = new JButton("Back");
		btn_submit = new JButton("Submit");
		
		/* LABELS */
		lblUsername = new JLabel("USERNAME: ");
		txtUsername = new JTextField(20);
		lblPass = new JLabel("PASSWORD: ");
		txtPassRecover = new JPasswordField(20);
		
		/* DEFINIR PANELES PRINCIPALES */
		getContentPane().add(pCenter, BorderLayout.CENTER);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pEast, BorderLayout.EAST);
		getContentPane().add(pWest, BorderLayout.WEST);
		
		/* AGREGAR ELEMENTOS A LOS PANELES */
		pUsername.add(lblUsername);
		pUsername.add(txtUsername);
		pPassword.add(lblPass);
		pPassword.add(txtPassRecover);
		pCenter.add(pUsername);
		pCenter.add(pPassword);
		pSouth.add(btn_back);
		pSouth.add(btn_submit);
		
		/* DB */
	    DB db_u = new DB();
	   
		
		/* EVENTS */
		/* BTN_BACK
		 * Boton que presionas, oculta la ventana actual y posteriormente activa la ventana anterior.
		 */
		btn_back.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
		});
		
		/* BTN_SUBMIT
		 * Boton que presionas, mira si existe el usuario y en ese caso cambia los datos que haya en el fichero.
		 */
		btn_submit.addActionListener(e -> {
			 db_u.connectJDBC("resources\\db\\db_proyecto.db");
			boolean edit = db_u.editUserPassword(txtUsername.getText(), txtPassRecover.getText());
			if(edit) {
		    	System.out.println("Se ha editado la contraseña correctamente correctamente.");
				JOptionPane.showMessageDialog(null, "Has cambiado la contraseña correctamente.");
				wCurrent.dispose();
				new WindowLogin(wCurrent);
			} else{
	    		JOptionPane.showMessageDialog(null, "Confirme que el usuario es el mismo al que ha insertado.");
	    		vaciarCampos();
	    		System.out.println("No se ha podido editar la contraseña.");
	    	}
			db_u.disconnectJDBC();
		});
		
		/* VISIBILIDAD */
		setVisible(true);
	}
	public void vaciarCampos () {
		txtUsername.setText("");
		txtPassRecover.setText("");
	}
}
