package utils.tablemodel;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import components.Users;

public class UsersTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	private List<Users> lUsers;
	private List<String> lTitles = Arrays.asList("Username", "Password", "Email", "Admin");
	
	public UsersTableModel(List<Users> lUsers) {
		this.lUsers = lUsers;
	}
	
	@Override
	public int getRowCount() {
		if(lUsers == null)
			return 0;
		return lUsers.size();
	}

	@Override
	public int getColumnCount() {
		return lTitles.size();
	}

	@Override
	public String getColumnName(int column) {
		return lTitles.get(column);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Users u = lUsers.get(row);
		switch(column) {
			case 0: return u.getUsername();
			case 1: return u.getPass();
			case 2: return u.getEmail();
			case 3: return u.getAdmin();
			default : return null;
		}
	}
}
