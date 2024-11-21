package utils.tablemodel;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import components.Series;

public class SeriesTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	private List<Series> lSeries;
	private List<String> lTitulos = Arrays.asList("NOMBRE","TEMPORADAS","CAPITULOS");

}
