package chat;

public class WindowChat {
    public static void main(String[] args) {
        // Ejecutar el servidor en un hilo
        Thread serverThread = new Thread(() -> {
            try {
                ChatServer.main(null);
            } catch (Exception e) {
                System.err.println("Error al iniciar el servidor: " + e.getMessage());
            }
        });
	
    }
    
    
}

