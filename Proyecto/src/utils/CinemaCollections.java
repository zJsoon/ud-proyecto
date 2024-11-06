package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import components.Films;
import components.Series;
import components.Users;

public class CinemaCollections {
	/*SERIES ATTRIBUTES*/
	private static ArrayList<Series> aSeries = new ArrayList<>();
	
	/** Gets the array list of all series
	 * @return ArrayList
	 */
	public static ArrayList<Series> getaSeries() {
		return aSeries;
	}
	
	/*FILMS ATTRIBUTES*/
	private static ArrayList<Films> aFilms = new ArrayList<>();
	
	/** Gets the array list of all movies
	 * @return ArrayList
	 */
	public static ArrayList<Films> getaFilms() {
		return aFilms;
	}

	/* ************ */
	/*GLOBAL METHODS*/
	/* ************ */

	/** Cleans the array lists of series and movies
	 * 
	 */
	public static void clear() {
		aSeries.removeAll(aSeries);
		aFilms.removeAll(aFilms);
	}
	
	/** Delete a movie or series
	 * @param nomfich	Name of the file
	 * @param u			User who is logged in
	 * @param desc		Name of the image of the selected series or film
	 */
	public static void deleteCinema(String nomfich, Users u, String desc) {
		int cont = 0;
		try {
			File faux = new File("aux.txt");
			PrintWriter pw = new PrintWriter(faux);
			File f = new File(nomfich);
			Scanner sc = new Scanner(new FileInputStream(f));
			String linea;
			while(sc.hasNextLine()) {
				linea = sc.nextLine();
				String[] partes = linea.split("\t");
				if (!partes[0].equals(u.getUsername()) || !partes[1].equals(desc)) {
					pw.println(linea);
				}else{
					if(cont>0) {
						pw.println(linea);
					}
					cont++;
				}
			}
			pw.flush();
			pw.close();
			sc.close();
			f.delete();
			faux.renameTo(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/* ************ */
	/*SERIES METHODS*/
	/* ************ */
	
	/**	Load all the series that are in the file db-fotoSeries.txt
	 * @param nomfich	Name of the file
	 */
	public static void loadSeries(String nomfich) {
		clear();
		try {
			Scanner sc = new Scanner(new File(nomfich));
			@SuppressWarnings("unused")
			String titulos = sc.nextLine();
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] partes = linea.split("\t");
				Series s = new Series(partes[0], partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), Integer.parseInt(partes[5]));
				aSeries.add(s);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** Loads all series that the user has stored in db-users-series.txt
	 * @param nomfich	Name of the file
	 * @param u			User who is logged in
	 */
	public static void loadSeriesUsers(String nomfich, Users u) {
		clear();
		try {
			Scanner sc = new Scanner(new File(nomfich));
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				System.out.println(linea);
				String[] partes = linea.split("\t");
				if (partes[0].equals(u.getUsername())) {
					Series s = new Series(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), partes[5], Boolean.parseBoolean(partes[6]), Integer.parseInt(partes[7]), Integer.parseInt(partes[8]));
					aSeries.add(s);
				}
			}
			sc.close();
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		}
	}
	
	/** Get all the series in the arraylist and create with the Series method a new object of type series.
	 * @param nombreFichero		Name of the file
	 * @return					Object Series
	 */
	public static Series getSeries(String nombreFichero) {
		boolean enc = false;
		int pos = 0;
		Series s = null;
		while (!enc && pos < aSeries.size()) {
			s = aSeries.get(pos);
			if (s.getImgCover().equals(nombreFichero))
				enc = true;
			else
				pos++;
		}
		if (!enc)
			return null;
		else
			return s;
	}
	
	/* ************ */
	/*FILMS METHODS*/
	/* ************ */
	
	/**	Load all the films that are in the file db-fotoFilms.txt
	 * @param nomfich	Name of the file
	 */
	public static void loadFilms(String nomfich) {
		clear();
		try {
			Scanner sc = new Scanner(new File(nomfich));
			@SuppressWarnings("unused")
			String titulos = sc.nextLine();
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] partes = linea.split("\t");
				Films p = new Films(partes[0], partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
				aFilms.add(p);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Loads all films that the user has stored in db-users-films.txt
	 * @param nomfich	Name of the file
	 * @param u			User who is logged in
	 */
	public static void loadFilmsUsers(String nomfich, Users u) {
		clear();
		try {
			Scanner sc = new Scanner(new File(nomfich));
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				System.out.println(linea);
				String[] partes = linea.split("\t");
				if (partes[0].equals(u.getUsername())) {
					Films p = new Films(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), partes[5], Boolean.parseBoolean(partes[6]), Integer.parseInt(partes[7]));
					aFilms.add(p);
				}
			}
			sc.close();
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		}
	}
	
	/** Get all the films in the arraylist and create with the Films method a new object of type films.
	 * @param nombreFichero		Name of the file
	 * @return					Object Films
	 */
	public static Films getFilms(String nombreFichero) {
		boolean enc = false;
		int pos = 0;
		Films p = null;
		while (!enc && pos < aFilms.size()) {
			p = aFilms.get(pos);
			if (p.getImgCover().equals(nombreFichero))
				enc = true;
			else
				pos++;
		}
		if (!enc)
			return null;
		else
			return p;
	}	
}