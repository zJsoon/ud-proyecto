package window.logged.admin;

import javax.swing.*;

import components.Users;

public class WindowAdmin extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;

	public WindowAdmin(JFrame wPrevious, Users u) {
		super();
		
		this.wPrevious = wPrevious;
		wCurrent = this;
		
		setVisible(true);
	}
}
