package window.logged.users.films;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import components.Films;
import components.Series;
import components.Users;
import utils.collections.CinemaCollections;

@SuppressWarnings("unused") // Dejamos esta linea de codigo porque hay codigo comentado que utiliza los imports y al estar comentado salen warnigns
public class WindowAddFilmsUser extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame wCurrent, wPrevious;
	
	private JPanel pCenter, pSouth, pNorth;
	private JButton btnClose;
	private JScrollPane scroll;
	private JLabel lblNothText;
	
	public WindowAddFilmsUser(JFrame wPrevious, Users u) {
		super();
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		/*WINDOW*/
		setBounds(200, 200, 800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		/*TITLE*/
		setTitle("TV | Films DataBase - Films Add");
		
		/*IMG TITLE*/
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		/*CREATE PANELS*/
		pSouth = new JPanel();
		pCenter = new JPanel(new GridLayout(0, 2));
		pNorth = new JPanel();
		scroll = new JScrollPane(pCenter);
		
		/*ADD COMPONENTS*/
		lblNothText = new JLabel("UD Students - Films Add");
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		pNorth.add(lblNothText);
		
		
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		
		btnClose = new JButton("CLOSE");
		pSouth.add(btnClose);
		
		loadFotos("data/db-films.txt");
		
		/*CREATE THREAD*/
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
		btnClose.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);	
		});
		
		/*MOUSE CLICKER SELECT IMG*/
		/* CODIGO FUNCIONAL, TENEMOS QUE ADAPTAR PARA QUE PASEN PARAMETROS DE USUARIO.
		pCenter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				JPanel pFoto = (JPanel) pCenter.getComponentAt(p);
				JLabel lblFoto = (JLabel) pFoto.getComponent(0);
				ImageIcon im = (ImageIcon) lblFoto.getIcon();
				Films films = CinemaCollections.getFilms(im.getDescription());
				try {
					PrintWriter pw = new PrintWriter(new FileWriter("data/db-users-films.txt", true));
					Scanner sc = new Scanner(new File("data/db-users-films.txt"));
					while(sc.hasNextLine()) {
						String linea = sc.nextLine();
						String [] partes = linea.split("\t");
						if(partes[0].equals(u.getUsername()) && partes[1].equals(films.getImgCover())) {
							JOptionPane.showMessageDialog(null, "The serie already added.", "Action blocked", JOptionPane.ERROR_MESSAGE);
							break;
						}else {							
							pw.println(u.getUsername() + films.getImgCover() + "\t" + films.getTitle() + "\t" + films.getYr() + "\t" + films.getRating() +  "\t" + films.getDuration()););
							break;
						}
					}
					sc.close();
					pw.close();
				} catch (FileNotFoundException err) {
					err.printStackTrace(); 
				} catch (IOException err) {
					err.printStackTrace();
				}
				dispose(); 
			}
		});*/
		
		/*VISIBILITY*/
		setVisible(true);
	}
	
	/** Takes the photos from a txt file and shows them on the screen.
	 * @param nomfich	File name
	 */
	public void loadFotos(String nomfich) {
		CinemaCollections.clear();
		CinemaCollections.loadSeries(nomfich);
		for(Films p: CinemaCollections.getaFilms()) {
			JLabel lblFoto = new JLabel();
			lblFoto.setSize(150,200);
			ImageIcon im = new ImageIcon("img/films/"+p.getImgCover());
			ImageIcon imgDimensions = new ImageIcon(im.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(),Image.SCALE_DEFAULT));
			imgDimensions.setDescription(p.getImgCover());
			lblFoto.setIcon(imgDimensions);
			JPanel pFoto = new JPanel();
			pFoto.add(lblFoto);
			pCenter.add(pFoto);
		}
	}

}
