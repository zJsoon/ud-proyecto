package window.logged.films.user;

import java.awt.*;
import javax.swing.*;

import components.Films;
import components.Users;
import utils.collections.CinemaCollections;

public class WindowViewFilmsUser extends JFrame {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;

	private JPanel pCenter, pSouth, pNorth;
	private JButton btnClose;
	private JScrollPane scroll;
	private JLabel lblNothText;

	public WindowViewFilmsUser(JFrame wPrevious, Users u) {
		super();
		wCurrent = this;
		this.wPrevious = wPrevious;

		/* WINDOW */
		setBounds(200, 200, 800, 600);
		setResizable(false);
		setLocationRelativeTo(null);

		/* TITLE */
		setTitle("TV | Films DataBase - Films View");

		/* TITLE IMG */
		ImageIcon im = new ImageIcon("img/logo.png");
		setIconImage(im.getImage());

		/* CREATE PANELS */
		pSouth = new JPanel();
		pCenter = new JPanel(new GridLayout(0, 2));
		pNorth = new JPanel();
		scroll = new JScrollPane(pCenter);

		/* ADD COMPONENTS */
		lblNothText = new JLabel("UD Students - Films View");
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		pNorth.add(lblNothText);

		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pNorth, BorderLayout.NORTH);

		btnClose = new JButton("CLOSE");
		pSouth.add(btnClose);

		//loadFotosDatabaseUser("./src/data/db-users-films.txt", u);

		/* THREAD CREATION */
		Runnable r = new Runnable() {
			@Override
			public void run() {
				int x = -lblNothText.getWidth();
				while (true) {
					x += 10;
					if (x > pNorth.getWidth()) {
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

		/* EVENTS */
		/* BTN CLOSE */
		btnClose.addActionListener(e -> {
			wCurrent.dispose();
			wPrevious.setVisible(true);
		});

		/* VISIBILITY */
		setVisible(true);
	}

	/**
	 * Takes the photos from a txt file that correspond to the user who is logged in
	 * and shows them on the screen.
	 * 
	 * @param nomfich File name
	 */
	public void loadFotosDatabaseUser(String nomfich, Users u) {
		CinemaCollections.loadSeriesUsers(nomfich, u);
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
			pCenter.add(pFoto);
		}
	}
}
