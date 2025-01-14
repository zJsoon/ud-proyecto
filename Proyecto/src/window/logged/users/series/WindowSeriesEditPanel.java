package window.logged.users.series;

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

import components.Series;
import components.Users;



public class WindowSeriesEditPanel extends JFrame{
	private JLabel lblFoto, lblTitle, lblYr, lblCalification, lblDuration, lblSeason;
	protected JTextField txtTitle, txtYr, txtCalification, txtDuration, txtSeasons;
	private JPanel pCenter,pFoto,pDatos,pSouth;
	private JButton btnEdit, btnClose;
	
	private static final long serialVersionUID = 1L;
	
	public WindowSeriesEditPanel(Users u, Series s) {
		super();
		/*WINDOW*/
		setBounds(250,250,700,500);
		setResizable(false);
		setLocationRelativeTo(null);
		
		/*TITLE*/
		setTitle("TV | Films DataBase - Series Edit | Serie: " + s.getTitle() + " | User: " + u.getUsername());
		ImageIcon im = new ImageIcon("img/logo.png");
		setIconImage(im.getImage());
		
		/*CREATE PANELES*/
		pCenter = new JPanel(new GridLayout(1, 2));
		pFoto = new JPanel();
		pDatos = new JPanel(new GridLayout(5, 1));
		pSouth = new JPanel();
		
		/*ADD COMPONENTS*/
		lblFoto = new JLabel();
		lblFoto.setSize(150,200);
		ImageIcon port = new ImageIcon("img/series/"+s.getImgCover());
		ImageIcon imgDimensions = new ImageIcon(port.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(),Image.SCALE_DEFAULT));
		imgDimensions.setDescription(s.getImgCover());
		lblFoto.setIcon(imgDimensions);
		
		
		lblTitle = new JLabel("Title: ");
		txtTitle = new JTextField();
		txtTitle.setText(s.getTitle());
		lblYr = new JLabel("Year: ");
		txtYr = new JTextField();
		txtYr.setText(String.valueOf(s.getYr()));
		lblCalification = new JLabel("Calificacion: ");
		txtCalification = new JTextField(String.valueOf(s.getRating()));
		lblDuration = new JLabel("Duracion Media: ");
		txtDuration = new JTextField(String.valueOf(s.getAverageDuration()));
		lblSeason = new JLabel("Temporadas: ");
		txtSeasons = new JTextField(String.valueOf(s.getSeasons()));
		
		
	
		pFoto.add(lblFoto);
		
		pDatos.add(lblTitle);
		pDatos.add(txtTitle);
		
		pDatos.add(lblYr);
		pDatos.add(txtYr);
		
		pDatos.add(lblCalification);
		pDatos.add(txtCalification);
		
		pDatos.add(lblDuration);
		pDatos.add(txtDuration);
		
		pDatos.add(lblSeason);
		pDatos.add(txtSeasons);
		
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
					String newLine = u.getUsername() + "\t" + s.getImgCover() + "\t" + txtTitle.getText() + "\t" + Integer.parseInt(txtYr.getText()) + "\t" + Integer.parseInt(txtCalification.getText()) + "\t" + Integer.parseInt(txtDuration.getText()) + "\t" + Integer.parseInt(txtSeasons.getText());
					File faux = new File("aux.txt");
					PrintWriter pw = new PrintWriter(faux);
					File f = new File("src/data/db-users-series.txt");
					Scanner sc = new Scanner(new FileInputStream(f));
					String linea;
					while(sc.hasNextLine()) {
						linea = sc.nextLine();
						String[] partes = linea.split("\t");
						if (partes[0].equals(u.getUsername()) && partes[1].equals(s.getImgCover())){
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