package ChatClient;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private Socket socket = null;
    private BufferedReader inputConsole;
    private BufferedReader inputServer;
    private PrintWriter output;
    //private static final int SERVER_PORT = 8000;
    //private static final String SERVER_ADDRESS = "127.0.0.1";

    public Client(String address, int port){
        try {
            Socket socket = new Socket(address, port);
            System.out.println("Client connected to the server!");

            inputConsole = new BufferedReader(new InputStreamReader(System.in));
            inputServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());

            String line = "";
            while(!line.equalsIgnoreCase("quit")){
                line = inputConsole.readLine();
                output.println(line);
                //System.out.println(inputServer.readLine());
            }

            socket.close();
            inputServer.close();
            inputConsole.close();
            output.close();
        }
        catch (UnknownHostException ex){
            System.out.println("Unknown host: " + ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println("Problem occurred: " + ex.getMessage());
        }
//        finally {
//            try{
//                socket.close();
//               inputServer.close();
//               inputConsole.close();
//               output.close();
//            }
//            catch (IOException ex){
//                System.out.println("Problem occurred: " + ex.getMessage());
//            }
//        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 8000);
    }
}
