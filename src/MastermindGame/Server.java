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
public class Server
{
    private int intNumClients;
    private final int MAXCLIENTS = 2;
    private ClientThread[] clients = new ClientThread[2];
    private HashMap<Integer, Phrase> phrases;
    private HashMap<Integer, Game> games;
    
    public Server(){
        intNumClients = 0;
        phrases = new HashMap<Integer, Phrase>();
        games = new HashMap<Integer, Game>();
    }
    
    public void runServer(int intPortNumber){
        try{
            ServerSocket server = new ServerSocket(intPortNumber, MAXCLIENTS);
            while(true){
                clients[intNumClients] = new ClientThread(server.accept(), intNumClients);
                intNumClients++;
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
                correctPhrase = (Phrase) in.readObject();
                phrases.put(intNumClients, correctPhrase);
                if(intClientNum==0) {
                games.put(1, new Game(correctPhrase));
                }else if(intClientNum==1) {
                	games.put(0, new Game(correctPhrase));
                }
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
        
        public void run(){
            Phrase phrase = null;

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
