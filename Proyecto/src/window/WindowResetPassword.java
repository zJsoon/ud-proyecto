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
	//public WindowResetPassword(JFrame wPrevious) {
	@SuppressWarnings("deprecation")
	public WindowResetPassword(JFrame wPrevious) {
		super();
		
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		setBounds(200,200,600,400);
		
		setTitle("UD Students - Reset Password");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		pCenter = new JPanel();
		pUsername = new JPanel();
		pPassword = new JPanel();
		pNorth = new JPanel();
		pSouth = new JPanel();
		pEast = new JPanel();
		pWest = new JPanel();
		pCenter.setLayout(new GridLayout(2,1));
		lblUsername = new JLabel("USERNAME: ");
		txtUsername = new JTextField(20);
		lblPass = new JLabel("PASSWORD: ");
		txtPassRecover = new JPasswordField(20);
		btn_back = new JButton("Back");
		btn_submit = new JButton("Submit");
		
		pUsername.add(lblUsername);
		pUsername.add(txtUsername);
		pPassword.add(lblPass);
		pPassword.add(txtPassRecover);
		
		pCenter.add(pUsername);
		pCenter.add(pPassword);
		
		getContentPane().add(pCenter, BorderLayout.CENTER);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pEast, BorderLayout.EAST);
		getContentPane().add(pWest, BorderLayout.WEST);
		
		
		pSouth.add(btn_back);
		pSouth.add(btn_submit);
		
		
		btn_back.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
		});
		
		btn_submit.addActionListener(e -> {
			Boolean find = false;
			
			String user = txtUsername.getText();
			String password = txtPassRecover.getText();
			Users u = new Users(user, password);
			
			String linea;
			String [] sp = null;
			
			try {
				BufferedReader br = new BufferedReader(new FileReader("./src/data/db-users.txt"));
				
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
		setVisible(true);
	}
}
