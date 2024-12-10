package window.logged.admin;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import components.Users;
import utils.collections.ConnectionDB;
import utils.tablemodel.UsersTableModel;

public class WindowAdmin extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	private JPanel pWest;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private List<String> lAttributes = Arrays.asList("user", "admins", "series", "films", "series_user", "films_users");
	private JScrollPane scrollTree;
	
	private UsersTableModel userModel;
	private JTable userTabla;
	@SuppressWarnings("unused")
	private JScrollPane userScroll;
	
	public WindowAdmin(JFrame wPrevious, Users u) {
		super();
		
		this.wPrevious = wPrevious;
		wCurrent = this;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("UD Students - Admin");
		setBounds(200, 200, 600, 400);
		setResizable(false);
		ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
		setIconImage(imagen.getImage());
		
		pWest = new JPanel();
		/*Creación del JTree*/
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("ADMIN PANEL");
		treeModel = new DefaultTreeModel(raiz);
		tree = new JTree(treeModel);
		scrollTree = new JScrollPane(tree);
		pWest.add(scrollTree);
		
		/* DB */
		ConnectionDB db_u = new ConnectionDB();
		
		db_u.connectJDBC("resources\\db\\db_proyecto.db");
		
		List<Users> lu = db_u.obtainUsers();
		
		userModel = new UsersTableModel(null);
		userTabla = new JTable(userModel);
		userScroll = new JScrollPane(userTabla);
	    
		getContentPane().add(pWest, BorderLayout.WEST);
		getContentPane().add(userScroll, BorderLayout.CENTER);
		
		tree.addTreeSelectionListener( e -> {
			TreePath tp = e.getPath();
			String p = tp.getLastPathComponent().toString();
			if(p.equals("user")) {
				userModel = new UsersTableModel(lu);
				userTabla.setModel(userModel);
			}
		});
		db_u.disconnectJDBC();
		
		setVisible(true);
	}
	
	public void loadTree() {
		for(int pos = 0;lAttributes.size()<pos;pos++) {
			DefaultMutableTreeNode newMTN = new DefaultMutableTreeNode(lAttributes.get(pos));
			treeModel.insertNodeInto(newMTN, (MutableTreeNode) treeModel.getRoot(), pos);
		}
	}
}
