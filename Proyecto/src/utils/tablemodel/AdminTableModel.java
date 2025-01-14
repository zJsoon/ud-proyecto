package utils.tablemodel;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import components.Users;

public class AdminTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	private List<Users> lAdmins;
	private List<String> lTitles = Arrays.asList("Username", "Password", "Email");
	
	public AdminTableModel(List<Users> lAdmins) {
		this.lAdmins = lAdmins;
	}
	
	public int getRowCount() {
		if(lAdmins == null)
			return 0;
		return lAdmins.size();
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
		Users u = lAdmins.get(row);
		switch(column) {
			case 0: return u.getUsername();
			case 1: return u.getPass();
			case 2: return u.getEmail();
			default : return null;
		}
	}
}
