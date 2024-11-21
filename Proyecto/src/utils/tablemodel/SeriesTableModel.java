package utils.tablemodel;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import components.Series;

public class SeriesTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	private List<Series> lSeries;
	private List<String> lTitulos = Arrays.asList("NOMBRE","TEMPORADAS","DURACION MEDIA");
	
	public SeriesTableModel(List<Series> ls) {
		lSeries = ls;
	}
	
	@Override
	public int getRowCount() {
		if (lSeries == null)
			return 0;
		return lSeries.size();
	}
	
	@Override
	public int getColumnCount() {
		return lTitulos.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return lTitulos.get(column);
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		Series s = lSeries.get(row);
		switch (column) {
		case 0:
			return s.getTitle();
		case 1:
			return s.getSeasons();
		case 2:
			return s.getAverageDuration();
		default:
			return null;
		}
	}

}
