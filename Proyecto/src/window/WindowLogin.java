package window;
import javax.swing.*;

public class WindowLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame wCurrent, wPrevious;
	public WindowLogin(JFrame wPrevious) {
		super();
		
		setBounds(200,200,600,400);
		
		setTitle("UD Students - Main");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		setVisible(true);
	}
}
