package window;

import javax.swing.*;

public class WindowMain extends JFrame{
	private static final long serialVersionUID = 1L;
	public WindowMain() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setBounds(200,200,600,400);
		
		setTitle("UD Students - Main");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		setVisible(true);
	}
}
