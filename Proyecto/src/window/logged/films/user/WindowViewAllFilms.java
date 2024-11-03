package window.logged.films.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.Films;
import utils.CinemaCollections;

public class WindowViewAllFilms extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel pNorth, pCentre, pSouth;
	private JLabel lblNothText;
	private JButton btnClose, btnReverse;
	
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	
	public WindowViewAllFilms (JFrame wPrevious) {
		super();
		
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		/*WINDOW*/
		setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setTitle("UD Students - Films View All");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		/*CREATE PANELS*/
		pNorth = new JPanel();
		pCentre = new JPanel();
		pSouth = new JPanel();
		
		/*CREATE BUTTONS*/
		btnClose = new JButton("Close");
		btnReverse = new JButton("Reverse");
		
		/* LABELS */
		lblNothText = new JLabel("UD Students - Films View All");
		
		/*ADD COMPONENTS*/
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		pNorth.setBackground(Color.black);
		pNorth.add(lblNothText);
		
		/* DEFINIR PANELES PRINCIPALES */
		getContentPane().add(pNorth, BorderLayout.NORTH);
		getContentPane().add(pCentre, BorderLayout.CENTER);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		
		/* EDITAR PANELES */
		pCentre.setLayout(new GridBagLayout());
		pCentre.setBackground(Color.BLACK);
		pSouth.setBackground(Color.BLACK);
		
		/* AGREGAR ELEMENTOS A LOS PANELES */
		pSouth.add(btnReverse);
		pSouth.add(btnClose);
		
		loadFotosFilmsAll("./src/data/db-films.txt");
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
	
	/**
	 * Takes the photos from a txt file and shows them on the screen.
	 * 
	 * @param nomfich File name
	 */
	public void loadFotosFilmsAll(String nomfich) {
		CinemaCollections.loadFilms(nomfich);
		for (Films p : CinemaCollections.getaFilms()) {
			JLabel lblFoto = new JLabel();
			lblFoto.setSize(150, 200);
			ImageIcon im = new ImageIcon("img/films/" + p.getImgCover());
			ImageIcon imagenConDimensiones = new ImageIcon(
					im.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
			imagenConDimensiones.setDescription(p.getImgCover());
			lblFoto.setIcon(imagenConDimensiones);
			JPanel pFoto = new JPanel();
			pFoto.add(lblFoto);
			pFoto.setBackground(Color.BLACK);
			pCentre.add(pFoto);
		}
	}
}
