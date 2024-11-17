package ChatClient;

import java.io.*;
import java.net.*;
import java.util.*;
import ChatClient.Client;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();
    private static final int SERVER_PORT = 8000;
    private static final String SERVER_ADDRESS = "127.0.0.1";

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server was created. Waiting for users connection...");

        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected");

            ClientHandler cl = new ClientHandler(clientSocket, clients);
            clients.add(cl);
            new Thread(cl).start();
        }
    }
}