package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            int number = 0;
            ServerSocket serverSocket = new ServerSocket(8055);
            System.out.println("Сервер начал работу...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostName() + "подключен");
                number++;
                System.out.println("Подключился пользователь номер: " + number);
                ServerThread serverThread = new ServerThread(socket, number);
                serverThread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }
}
