package window.logged.users.films;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import components.Films;
import components.Users;
import utils.collections.CinemaCollections;

public class WindowFilmsEdit extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pCenter, pSouth, pNorth;
	private JButton btnClose;
	private JScrollPane scroll;
	private JLabel lblNothText;

	public WindowFilmsEdit(JFrame wPrevious, Users u) {
		super();
		/*WINDOW*/
		setBounds(200, 200, 800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		/*TITLE*/
		setTitle("TV | Films DataBase - Films Edit | User: " + u.getUsername());
		
		/*TITLE IMG*/
		ImageIcon im = new ImageIcon("img/logo.png");
		setIconImage(im.getImage());
		
		/*CREATE PANELS*/
		pSouth = new JPanel();
		pCenter = new JPanel(new GridLayout(0, 2));
		pNorth = new JPanel();
		scroll = new JScrollPane(pCenter);
		
		/*ADD COMPONENTS*/
		lblNothText = new JLabel("UD Students - Films Edit");
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		pNorth.add(lblNothText);
		
		
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		
		btnClose = new JButton("CLOSE");
		pSouth.add(btnClose);
		
		loadFotosDatabaseUser("src/data/db-users-films.txt", u);
		
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
		
		/*EVENTS*/
		/*BTN CLOSE*/
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		/*MOUSE CLICKER SELECT IMG*/
		pCenter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				JPanel pFoto = (JPanel) pCenter.getComponentAt(p);
				JLabel lblFoto = (JLabel) pFoto.getComponent(0);
				ImageIcon im = (ImageIcon) lblFoto.getIcon();
				System.out.println(im.getDescription());
				Films films = CinemaCollections.getFilms(im.getDescription());
				new WindowFilmsEditPanel(u, films);
			}
		});
		
		/*VISIBILITY*/
		setVisible(true);
	}
	
	/** Takes the photos from a txt file that correspond to the user who is logged in and shows them on the screen.
	 * @param nomfich	File name
	 */
	public void loadFotosDatabaseUser(String nomfich, Users u) {
		CinemaCollections.loadFilmsUsers(nomfich, u);
		for(Films f: CinemaCollections.getaFilms()) {
			JLabel lblFoto = new JLabel();
			lblFoto.setSize(150,200);
			ImageIcon im = new ImageIcon("img/films/"+f.getImgCover());
			ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(),Image.SCALE_DEFAULT));
			imagenConDimensiones.setDescription(f.getImgCover());
			lblFoto.setIcon(imagenConDimensiones);
			JPanel pFoto = new JPanel();
			pFoto.add(lblFoto);
			pCenter.add(pFoto);
		}
	}
}