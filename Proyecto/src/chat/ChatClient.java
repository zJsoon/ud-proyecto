package chat;

import java.io.*;
import java.net.*;

public class ChatClient {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 12345);
				BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

			System.out.println("Conectado al servidor. Escribe tus mensajes...");

			// Hilo para escuchar mensajes del servidor
			new Thread(() -> {
				try {
					String message;
					while ((message = in.readLine()) != null) {
						System.out.println("Mensaje recibido: " + message);
					}
				} catch (IOException e) {
					System.out.println("Conexión cerrada.");
				}
			}).start();

			// Enviar mensajes al servidor
			String userInput;
			while ((userInput = keyboard.readLine()) != null) {
				out.println(userInput);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
// The ChatClient class establishes	
// a connection with the server and
// creates two threads: one to listen
