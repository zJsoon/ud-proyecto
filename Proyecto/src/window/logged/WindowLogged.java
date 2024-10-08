package window.logged;

import java.awt.*;

import javax.swing.*;

import window.WindowMain;

public class WindowLogged extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	
	private JPanel pCenter, pCenterLeft, pCenterRight, pNorth, pSouth, pEast, pWest;
	
	private JButton btn_exit, btn_films, btn_series;
	
	private JLabel lblNothText;
	
	public WindowLogged(JFrame wPrevious) {
		super();
		
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("UD Students - Main");
		setBounds(200, 200, 600, 400);
		setResizable(false);
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		/* PANELS */
		pCenter = new JPanel();
		pCenterLeft = new JPanel();
		pCenterRight = new JPanel();
		pNorth = new JPanel();
		pSouth = new JPanel();
		pEast = new JPanel();
		pWest = new JPanel();
		
		/* BUTTONS */
		btn_exit = new JButton("Exit");
		btn_films = new JButton("Films");
		btn_series = new JButton("Series");
		
		/* LABELS */
		lblNothText = new JLabel("UD Students - Films/Series");
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		
		/* DEFINIR PANELES PRINCIPALES */
		getContentPane().add(pCenter, BorderLayout.CENTER);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		getContentPane().add(pEast, BorderLayout.EAST);
		getContentPane().add(pWest, BorderLayout.WEST);
		
		/* AGREGAR ELEMENTOS A LOS PANELES */
		pCenterLeft.setLayout(new FlowLayout(FlowLayout.CENTER));
		pCenterRight.setLayout(new FlowLayout(FlowLayout.CENTER));
		pCenterLeft.add(btn_films);
		pCenterRight.add(btn_series);
		pCenter.add(pCenterLeft);
		pCenter.add(pCenterRight);
		pNorth.add(lblNothText);
		pSouth.add(btn_exit);
		
		btn_exit.addActionListener(e -> {
			wCurrent.dispose();
			new WindowMain();
		});
		
		/* THREAD CREATE
		 * Rotacion de texto que pone UD Students - Films/Series
		 */
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
		
		/* VISIBILIDAD */
		setVisible(true);
	}
}
