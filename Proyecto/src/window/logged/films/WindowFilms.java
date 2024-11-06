package window.logged.films;

import java.awt.*;
import javax.swing.*;

import window.logged.films.user.WindowAddFilmsUser;
import window.logged.films.user.WindowViewAllFilms;
import window.logged.films.user.WindowViewFilmsUser;

public class WindowFilms extends JFrame{
private static final long serialVersionUID = 1L;
	
	private JPanel pNorth, pCentre, pSouth;
	private JLabel lblNothText;
	private JButton btnClose, btnReverse, btnAddFilms, btnEditFilms, btnDeleteFilms, btnViewFilms, btnViewAllFilms;
	
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	
	public WindowFilms(JFrame wPrevious) {
		super();
		
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		/*WINDOW*/
		setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setTitle("UD Students - Films DataBase");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		/*CREATE PANELS*/
		pNorth = new JPanel();
		pCentre = new JPanel();
		pSouth = new JPanel();
		
		/*CREATE BUTTONS*/
		btnViewAllFilms = new JButton("View All Films");
		btnAddFilms = new JButton("Add Films");
		btnEditFilms = new JButton("Edit Films");
		btnDeleteFilms = new JButton("Delete Films");
		btnViewFilms = new JButton("View Your DataBase");
		btnReverse = new JButton("Reverse");
		btnClose = new JButton("Close");
		/* LABELS */
		lblNothText = new JLabel("UD Students - Films");
		
		/*ADD COMPONENTS*/
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		pNorth.setBackground(Color.black);
		pNorth.add(lblNothText);
		
		pCentre.add(btnViewAllFilms);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnAddFilms);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnEditFilms);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnDeleteFilms);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnViewFilms);
		pSouth.add(btnReverse);
		pSouth.add(btnClose);
		
		/* DEFINIR PANELES PRINCIPALES */
		getContentPane().add(pNorth, BorderLayout.NORTH);
		getContentPane().add(pCentre, BorderLayout.CENTER);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		
		/* EDITAR PANELES */
		pCentre.setLayout(new GridBagLayout());
		pCentre.setBackground(Color.BLACK);
		pSouth.setBackground(Color.BLACK);
		
		/* AGREGAR ELEMENTOS A LOS PANELES */
		pSouth.add(btnClose);
		
		/*EVENTS*/
		/*BUTTON CLOSE*/
		btnClose.addActionListener(e -> {
			System.exit(0);
		});
		
		/*BUTTON REVERSE*/
		btnReverse.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
		});
		
		/*BUTTON VIEW FILMS*/
		btnViewFilms.addActionListener(e -> {
			wCurrent.dispose();
			new WindowViewFilmsUser(wCurrent);
		});
		
		btnViewAllFilms.addActionListener(e -> {
			wCurrent.dispose();
			new WindowViewAllFilms(wCurrent);
		});
		
		btnAddFilms.addActionListener(e -> {
			wCurrent.dispose();
			new WindowAddFilmsUser(wCurrent);
		});
		/*THREAD CREATION*/
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
		
		/*VISIBILITY*/
		setVisible(true);
	}
}
