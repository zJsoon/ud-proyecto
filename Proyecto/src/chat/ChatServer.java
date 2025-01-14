package chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	private static Set<PrintWriter> clientWriters = new HashSet<>();

	public static void main(String[] args) {
		System.out.println("Servidor iniciado...");
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Nuevo cliente conectado...");
				new Thread(new ClientHandler(clientSocket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ClientHandler implements Runnable {
		private Socket socket;
		private PrintWriter out;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
				out = new PrintWriter(socket.getOutputStream(), true);
				synchronized (clientWriters) {
					clientWriters.add(out);
				}
				String message;
				while ((message = in.readLine()) != null) {
					System.out.println("Mensaje recibido: " + message);
					broadcastMessage(message);
				}
			} catch (IOException e) {
				System.out.println("Error en la conexión: " + e.getMessage());
			} finally {
				synchronized (clientWriters) {
					clientWriters.remove(out);
				}
			}
		}

		private void broadcastMessage(String message) {
			synchronized (clientWriters) {
				for (PrintWriter writer : clientWriters) {
					writer.println(message);
				}
			}
		}
	}
}
