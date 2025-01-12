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
import utils.collections.DB;
import utils.tablemodel.AdminTableModel;
import utils.tablemodel.UsersTableModel;

public class WindowAdmin extends JFrame{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JFrame wCurrent, wPrevious;
	private JPanel pWest,pCenter;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private List<String> lAttributes = Arrays.asList("user", "admins", "series", "films", "series_user", "films_users");
	private JScrollPane scrollTree;
	
	private UsersTableModel userModel;
	private AdminTableModel adminModel;
	private JTable userTabla, adminTabla;
	private JScrollPane userScroll, adminScroll;
	
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
		pCenter = new JPanel();
		/*Creación del JTree*/
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("ADMIN PANEL");
		treeModel = new DefaultTreeModel(raiz);
		tree = new JTree(treeModel);
		scrollTree = new JScrollPane(tree);
		loadTree();
		pWest.add(scrollTree);
		
		/* DB */
		DB db_u = new DB();
		db_u.connectJDBC("resources\\db\\db_proyecto.db");
		//System.out.println(lAttributes.size());
		List<Users> lu = db_u.obtainUsers();
		List<Users> la = db_u.obtainUsersAdmin();
		
		userModel = new UsersTableModel(null);
		userTabla = new JTable(userModel);
		userScroll = new JScrollPane(userTabla);
		
		adminModel = new AdminTableModel(null);
		adminTabla = new JTable(adminModel);
		adminScroll = new JScrollPane(adminTabla);
		
		getContentPane().add(pWest, BorderLayout.WEST);
		getContentPane().add(pCenter, BorderLayout.CENTER);
		
		tree.addTreeSelectionListener( e -> {
			TreePath tp = e.getPath();
			String p = tp.getLastPathComponent().toString();
			if(p.equals("user")) {
				userModel = new UsersTableModel(lu);
				userTabla.setModel(userModel);		
				getContentPane().add(userScroll, BorderLayout.CENTER);
			} else if (p.equals("admins")) {
				adminModel = new AdminTableModel(la);
				adminTabla.setModel(adminModel);	
				getContentPane().add(adminScroll, BorderLayout.CENTER);
			} else if (p.equals("series")) {
                userModel = new UsersTableModel(null);
                userTabla.setModel(userModel);
            } else if (p.equals("films")) {
                userModel = new UsersTableModel(null);
                userTabla.setModel(userModel);
            } else if (p.equals("series_users")) {
                userModel = new UsersTableModel(null);
                userTabla.setModel(userModel);
            } else if (p.equals("films_users")) {
                userModel = new UsersTableModel(null);
                userTabla.setModel(userModel);
            }
		});
		db_u.disconnectJDBC();
		
		setVisible(true);
	}
	
	public void loadTree() {
		for(int pos = 0;lAttributes.size()>pos;pos++) {
			DefaultMutableTreeNode newMTN = new DefaultMutableTreeNode(lAttributes.get(pos));
			treeModel.insertNodeInto(newMTN, (MutableTreeNode) treeModel.getRoot(), pos);
		}
	}
}
