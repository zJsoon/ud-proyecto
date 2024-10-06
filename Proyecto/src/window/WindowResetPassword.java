package window;

import java.awt.*;
import java.io.*;
import java.time.*;
import javax.swing.*;

import components.Users;

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
			Boolean find = false;
			
			String user = txtUsername.getText();
			String password = txtPassRecover.getText();
			Users u = new Users(user, password);
			
			String linea;
			String fich = "./src/data/db-users.txt";
			String [] sp = null;
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(fich));
				
				while(!find && (linea=br.readLine())!=null) {
					sp = linea.split(";");
					if(u.getUsername().equals(sp[0])) {
						find = true;
						wCurrent.dispose();
						new WindowLogin(wCurrent);
					}
				}
				if(find) {
					sp[1] = password;
					sp[2] = password;
					sp[5] = LocalDateTime.now().toString();
					
					JOptionPane.showMessageDialog(null, "You have successfully change your password.", "SUCCESSFUL!", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Not registered.", "ERROR.", JOptionPane.ERROR_MESSAGE);
				}

				br.close();
			} catch (FileNotFoundException err) {
				err.printStackTrace();
			} catch (IOException err) {
				err.printStackTrace();
			}
			
		});
		
		/* VISIBILIDAD */
		setVisible(true);
	}
}
