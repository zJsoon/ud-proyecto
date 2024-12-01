package db;

public class ConnectionDB {
	
	public static void ConnectJDBC() {
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Se ha podido cargar el driver de la DB");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la DB");
		}
	}
}
