package window.logged.users.series;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

import components.Series;
import components.Users;
import utils.collections.CinemaCollections;

@SuppressWarnings("unused") // Dejamos esta linea de codigo porque hay codigo comentado que utiliza los imports y al estar comentado salen warnigns
public class WindowAddSeriesUser extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JFrame wCurrent, wPrevious;
	
	private JPanel pCenter, pSouth, pNorth;
	private JButton btnClose;
	private JScrollPane scroll;
	private JLabel lblNothText;
	
	public WindowAddSeriesUser(JFrame wPrevious, Users u) {
		super();
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		/*WINDOW*/
		setBounds(200, 200, 800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		/*TITLE*/
		setTitle("TV | Films DataBase - Series Add");
		
		/*IMG TITLE*/
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		/*CREATE PANELS*/
		pSouth = new JPanel();
		pCenter = new JPanel(new GridLayout(0, 2));
		pNorth = new JPanel();
		scroll = new JScrollPane(pCenter);
		
		/*ADD COMPONENTS*/
		lblNothText = new JLabel("UD Students - Series Add");
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		pNorth.add(lblNothText);
		
		
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		
		btnClose = new JButton("CLOSE");
		pSouth.add(btnClose);
		
		loadFotos("data/db-series.txt");
		
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
				Series serie = CinemaCollections.getSeries(im.getDescription());
				try {
					PrintWriter pw = new PrintWriter(new FileWriter("data/db-users-series.txt", true));
					Scanner sc = new Scanner(new File("data/db-users-series.txt"));
					while(sc.hasNextLine()) {
						String linea = sc.nextLine();
						String [] partes = linea.split("\t");
						if(partes[0].equals(u.getUsername()) && partes[1].equals(serie.getImgCover())) {
							JOptionPane.showMessageDialog(null, "The serie already added.", "Action blocked", JOptionPane.ERROR_MESSAGE);
							break;
						}else {							
							pw.println(u.getUsername() + "\t" + serie.getImgCover() + "\t" + serie.getTitle() + "\t" + serie.getYr() + "\t" + serie.getRating() +  "\t" + serie.getAverageDuration() + "\t" + serie.getSeasons());
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
		for(Series s: CinemaCollections.getaSeries()) {
			JLabel lblFoto = new JLabel();
			lblFoto.setSize(150,200);
			ImageIcon im = new ImageIcon("img/series/"+s.getImgCover());
			ImageIcon imgDimensions = new ImageIcon(im.getImage().getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight(),Image.SCALE_DEFAULT));
			imgDimensions.setDescription(s.getImgCover());
			lblFoto.setIcon(imgDimensions);
			JPanel pFoto = new JPanel();
			pFoto.add(lblFoto);
			pCenter.add(pFoto);
		}
	}

}
