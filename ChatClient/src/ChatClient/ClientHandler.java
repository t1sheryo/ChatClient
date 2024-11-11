package ChatClient;

import java.io.*;
import java.net.*;
import java.util.*;

// class to spread messages from one client to others automatically
public class ClientHandler implements Runnable{
    private Socket socket;
    private List<ClientHandler> clients;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket s, List<ClientHandler> c)
        throws IOException
    {
        this.socket = s;
        this.clients = c;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
    }

    public void run(){
        try{
            String inputLine;
            while((inputLine = in.readLine()) != null){
                for(final var cl : clients){
                    cl.out.println(inputLine);
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Problem occurred: " + ex.getMessage());
        }
        finally {
            try{
                socket.close();
                in.close();
                out.close();
            }
            catch (IOException ex){
                System.out.println("Problem occurred: " + ex.getMessage());
            }
        }
        }
}
