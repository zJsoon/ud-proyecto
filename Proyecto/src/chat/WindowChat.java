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
        
        //Ejecutar el hilo
        Thread clientThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // Dar tiempo al servidor para que se inicie
                ChatClient.main(null);
            } catch (Exception e) {
                System.err.println("Error al iniciar el cliente: " + e.getMessage());
            }
        });
        
     // Iniciar los hilos
        serverThread.start();
        clientThread.start();
	
    }
    
    
}

