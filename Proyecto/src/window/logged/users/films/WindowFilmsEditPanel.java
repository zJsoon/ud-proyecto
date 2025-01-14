package window.logged.users.films;

	import java.awt.BorderLayout;
	import java.awt.GridLayout;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.PrintWriter;
	import java.util.Scanner;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextField;

import components.Films;
import components.Users;

	public class WindowFilmsEditPanel extends JFrame{
		private JLabel lblFoto, lblTitle, lblYr, lblCalification, lblDuration;
		protected JTextField txtTitle, txtYr, txtCalification, txtDuration;
		private JPanel pCenter,pFoto,pDatos,pSouth;
		private JButton btnEdit, btnClose;
		
		private static final long serialVersionUID = 1L;
		
		public WindowFilmsEditPanel(Users u, Films films) {
			super();
			/*WINDOW*/
			setBounds(250,250,700,500);
			setResizable(false);
			setLocationRelativeTo(null);
			
			/*TITLE*/
			setTitle("TV | Films DataBase - Films Edit | Film: " + films.getTitle() + " | User: " + u.getUsername());
			ImageIcon im = new ImageIcon("img/logo.png");
			setIconImage(im.getImage());
			
			/*CREATE PANELS*/
			pCenter = new JPanel(new GridLayout(1, 2));
			pFoto = new JPanel();
			pDatos = new JPanel(new GridLayout(5, 1));
			pSouth = new JPanel();
			
			/*ADD COMPONENTS*/
			lblFoto = new JLabel();
			lblFoto.setSize(150,200);
			ImageIcon port = new ImageIcon("img/films/"+films.getImgCover());
			ImageIcon imgDimensions = new ImageIcon(port.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(),Image.SCALE_DEFAULT));
			imgDimensions.setDescription(films.getImgCover());
			lblFoto.setIcon(imgDimensions);
			
			lblTitle = new JLabel("Title: ");
			txtTitle = new JTextField();
			txtTitle.setText(films.getTitle());
			lblYr = new JLabel("Year: ");
			txtYr = new JTextField();
			txtYr.setText(String.valueOf(films.getYr()));
			lblCalification = new JLabel("Calificacion: ");
			txtCalification = new JTextField(String.valueOf(films.getRating()));
			lblDuration = new JLabel("Duracion: ");
			txtDuration = new JTextField(String.valueOf(films.getDuration()));
			
			
			pDatos.add(lblTitle);
			pDatos.add(txtTitle);
			
			pDatos.add(lblYr);
			pDatos.add(txtYr);
			
			pDatos.add(lblCalification);
			pDatos.add(txtCalification);
			
			pDatos.add(lblDuration);
			pDatos.add(txtDuration);
			
			pCenter.add(pFoto);
			pCenter.add(pDatos);
			
			btnClose = new JButton("CLOSE");
			btnEdit = new JButton("EDIT");
			pSouth.add(btnEdit);
			pSouth.add(btnClose);
			
			getContentPane().add(pCenter,BorderLayout.CENTER);
			getContentPane().add(pSouth, BorderLayout.SOUTH);
			
			/*EVENTS*/
			/*BTN CLOSE*/
			btnClose.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			/*BTN EDIT*/
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					try {
						String newLine = u.getUsername() + "\t" + films.getImgCover() + "\t" + txtTitle.getText() + "\t" + Integer.parseInt(txtYr.getText()) + "\t" + Integer.parseInt(txtCalification.getText()) + "\t" + Integer.parseInt(txtDuration.getText());
						File faux = new File("aux.txt");
						PrintWriter pw = new PrintWriter(faux);
						File f = new File("src/data/db-users-films.txt");
						Scanner sc = new Scanner(new FileInputStream(f));
						String linea;
						while(sc.hasNextLine()) {
							linea = sc.nextLine();
							String[] partes = linea.split("\t");
							if (partes[0].equals(u.getUsername()) && partes[1].equals(films.getImgCover())){
								pw.println(newLine);
							}else{
								pw.println(linea);
							}
						}
						pw.flush();
						pw.close();
						sc.close();
						f.delete();
						faux.renameTo(f);
						dispose();
					}catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}	
					
				}
			});
			/*VISIBILITY*/
			setVisible(true);
		}
}
