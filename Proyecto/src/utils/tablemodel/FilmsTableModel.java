package utils.tablemodel;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import components.Films;

public class FilmsTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	private List<Films> lFilms;
	private List<String> lTitles = Arrays.asList("Title","Year","Rating","Duration");
	
	
	public FilmsTableModel(List<Films> lf) {
        lFilms = lf;
    }
	
	@Override
	public int getRowCount() {
		if (lFilms == null)
			return 0;
		return lFilms.size();
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
	public Object getValueAt(int row, int column) {
		Films f = lFilms.get(row);
		switch (column) {
		case 0:
			return f.getTitle();
		case 1:
			return f.getYr();
		case 2:
			return f.getRating();
		case 3:
			return f.getDuration();
		default:
			return null;
		}
	}
}