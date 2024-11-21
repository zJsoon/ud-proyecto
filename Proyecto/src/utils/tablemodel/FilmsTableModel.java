package utils.tablemodel;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import components.Films;

public class FilmsTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	private List<Films> lFilms;
	private List<String> lTitles = Arrays.asList("Title","Year","Rating","Duration");
	
	


