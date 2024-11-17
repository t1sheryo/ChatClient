package ChatClient;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.Consumer;

public class Client {
    private Socket socket = null;
    //private BufferedReader inputConsole;
    private BufferedReader inputServer;
    private PrintWriter output;
    private Consumer<String> onMessageReceived;
    //private static final int SERVER_PORT = 8000;
    //private static final String SERVER_ADDRESS = "127.0.0.1";

    public Client(String address, int port, Consumer<String> consumer){
        try {
            this.onMessageReceived = consumer;

            this.socket = new Socket(address, port);
            //System.out.println("Client connected to the server!");

            //inputConsole = new BufferedReader(new InputStreamReader(System.in));
            this.inputServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);

//            String line = "";
//            while(!line.equalsIgnoreCase("quit")){
//                line = inputConsole.readLine();
//                output.println(line);
//                //System.out.println(inputServer.readLine());
//            }
//
//            socket.close();
//            inputServer.close();
//            inputConsole.close();
//            output.close();
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

    public void sendMessage(String msg){
        output.println(msg);
    }

    public void startClient() {
        new Thread(() -> {
            try {
                String line;
                while ((line = inputServer.readLine()) != null) {
                    onMessageReceived.accept(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

//    public static void main(String args[]) {
//        Client client = new Client("127.0.0.1", 8000);
//    }
}
