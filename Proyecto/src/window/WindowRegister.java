package window;
import javax.swing.*;

public class WindowRegister extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame wCurrent, wPrevious;
	public WindowRegister(JFrame wPrevious) {
		super();
		
		setBounds(200,200,600,400);
		setTitle("UD Students - Register");
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		setVisible(true);
	}
}