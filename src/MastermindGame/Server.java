/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ctg5117
 */
public class Server implements Runnable
{
    private int intNumClients;
    private final int MAXCLIENTS = 2;
    private ClientThread[] clients = new ClientThread[2];
    private HashMap<Integer, Phrase> phrases;
    private HashMap<Integer, Game> games;
    private int intPort;
    
    public Server(){
        intNumClients = 0;
        phrases = new HashMap<Integer, Phrase>();
        games = new HashMap<Integer, Game>();
    }
    
     public Server(int intPort){
        intNumClients = 0;
        phrases = new HashMap<Integer, Phrase>();
        games = new HashMap<Integer, Game>();
        this.intPort = intPort;
    }
    
    public static void main(String[] args) {
        Server server = new Server();
        server.runServer(2000);
    }
    
    public void runServer(int intPortNumber){
        try{
            intPort = intPortNumber;
            ServerSocket server = new ServerSocket(intPortNumber, MAXCLIENTS);
            while(true){
                intNumClients++;
                clients[intNumClients-1] = new ClientThread(server.accept(), intNumClients-1);
                clients[intNumClients-1].start();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getPortNumber(){
        return intPort;
    }
    
    public void setPortNumber(int intPort){
        this.intPort = intPort;
    }

    @Override
    public void run() {
        try{
            ServerSocket server = new ServerSocket(intPort, MAXCLIENTS);
            while(true){
                intNumClients++;
                clients[intNumClients-1] = new ClientThread(server.accept(), intNumClients-1);
                clients[intNumClients-1].start();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private class ClientThread extends Thread{
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private Phrase correctPhrase;
        private int intClientNum;
        
        public ClientThread(Socket socket, int intClientNum){
            this.socket = socket;
            this.intClientNum = intClientNum;
            try{
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(socket.getInputStream());
                out.writeObject("Connection Successful");
                out.flush();
                String connectionMessage = (String) in.readObject();
                System.out.println(connectionMessage);
                
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void sendResponse(String[] response){
            try {
                out.writeObject(response);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void sendMessage(String message){
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void run(){
            try {
                correctPhrase = (Phrase) in.readObject();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
                phrases.put(intNumClients, correctPhrase);
                if(intClientNum==0) {
                games.put(1, new Game(correctPhrase));
                }else if(intClientNum==1) {
                	games.put(0, new Game(correctPhrase));
                }
            Phrase phrase = null;
            if (intClientNum == 0){
                clients[1].sendMessage("Phrase Recieved");
            }else if (intClientNum == 1){
                clients[0].sendMessage("Phrase Recieved");
            }

         // process connection
         try {

            // read message from client
            do {

               try {
                  phrase = (Phrase) in.readObject();
                  Game g = games.get(intClientNum);
                  String[] response = g.retrievePhrase(phrase);
                  System.out.println(Arrays.toString(response));
                  sendResponse(response);
               }

               // process problems reading from client
               catch ( ClassNotFoundException classNotFoundException ) {
                  
               }

            } while ( true );

           
         }

         // process problems with I/O
         catch ( IOException ioException ) {
            System.out.println( "Client terminated connection" );
         }

         // close streams and socket
         finally {

            try {
               out.close();
               in.close();
               socket.close();
            }

            // process problems with I/O
            catch ( IOException ioException ) {
               ioException.printStackTrace();
            }
         }
        }
    }
}
