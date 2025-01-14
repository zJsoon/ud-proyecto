package window.logged.users.series;

import java.awt.*;
import javax.swing.*;

import components.Users;

public class WindowSeries extends JFrame{
private static final long serialVersionUID = 1L;
	
	private JPanel pNorth, pCentre, pSouth;
	private JLabel lblNothText;
	private JButton btnClose, btnReverse, btnAddSeries, btnEditSeries, btnRemoveSeries, btnViewSeries, btnViewAllSeries;
	
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	
	public WindowSeries(JFrame wPrevious, Users u) {
		super();
		
		wCurrent = this;
		this.wPrevious = wPrevious;
		
		/*WINDOW*/
		setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setTitle("UD Students - Series DataBase");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		/*CREATE PANELS*/
		pNorth = new JPanel();
		pCentre = new JPanel();
		pSouth = new JPanel();
		
		/*CREATE BUTTONS*/
		btnViewAllSeries = new JButton("View All Series");
		btnAddSeries = new JButton("Add Series");
		btnEditSeries = new JButton("Edit Series");
		btnRemoveSeries = new JButton("Delete Series");
		btnViewSeries = new JButton("View Your DataBase");
		btnReverse = new JButton("Reverse");
		btnClose = new JButton("Close");
		/* LABELS */
		lblNothText = new JLabel("UD Students - Series");
		
		/*ADD COMPONENTS*/
		lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		lblNothText.setForeground(Color.CYAN);
		pNorth.setBackground(Color.black);
		pNorth.add(lblNothText);
		
		pCentre.add(btnViewAllSeries);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnAddSeries);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnEditSeries);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnRemoveSeries);
		pCentre.add(Box.createRigidArea(new Dimension(10, 10)));
		pCentre.add(btnViewSeries);
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
		/*BUTTON VIEW SERIES*/
		btnViewSeries.addActionListener(e -> {
			wCurrent.dispose();
			new WindowViewSeriesUser(wCurrent, u);
		});
		
		btnViewAllSeries.addActionListener(e -> {
			wCurrent.dispose();
			new WindowViewAllSeries(wCurrent, u);
		});
		
		
		btnAddSeries.addActionListener(e -> {
			wCurrent.dispose();
			new WindowAddSeriesUser(wCurrent, u);
		});
		
		btnEditSeries.addActionListener(e -> {
			wCurrent.dispose();
			new WindowSeriesEdit(wCurrent, u);
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
