/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ctg5117
 */
public class Client extends Thread
{
    private String strHost;
    private int intPort;
    private JPGame display;
    //Use run from this code http://cs.lmu.edu/~ray/notes/javanetexamples/
    
    //Open main menu
    public static void main(String[] args) {
        JFMainMenu mainMenu = new JFMainMenu();
        mainMenu.setVisible(true);
        Client client = new Client();
        JFGameDisplay game = new JFGameDisplay();
        game.setVisible(true);
        
    }
    //Select Join game -> get ip of server -> server connected -> get 
    
    //
    
    
    ObjectInputStream in;
    ObjectOutputStream out;
    
    /**
     * Create a client that will connect to a server
     */
    public Client(){
        String[] serverInformation = getServerAddress();
        String strIP = serverInformation[0];
        int intPort = Integer.parseInt(serverInformation[1]);
        this.strHost = strIP;
        this.intPort = intPort;
    }
    
    /**
     * Create a client on the same machine as the server
     * @param intPort 
     */
    public Client(int intPort){
        strHost = "127.0.0.1";
        this.intPort = intPort;
    }
    
    /**
     * Keep track of the JPGame to be able to update display information
     * @param display 
     */
    public void setDisplay(JPGame display){
        this.display = display;
    }
    
    /**
     * Use a JOptionPane to request the server IP and port
     * @return String array with IP and port
     */
    private static String[] getServerAddress()
    {
        JTextField ip = new JTextField();
        JTextField port = new JTextField();
        final JComponent[] inputs = new JComponent[]{
          new JLabel("Server IP: "),
          ip,
          new JLabel("Server Port: "),
          port
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Server Connection Information", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {
            String strHost = ip.getText();
            String strPort = port.getText();
            return new String[]{strHost, strPort};
        }
        return null;
    }
    
    /**
     * Runs when thread is started
     */
    @Override
    public void run(){
        try {
            run(strHost, intPort);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Main client logic runs in this method
     * @param strIP
     * @param intPort
     * @throws IOException 
     */
    private void run(String strIP, int intPort) throws IOException
    {

    // Make connection and initialize streams
        Socket socket = new Socket(strIP, intPort);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        String connectionMessage = null;
    //Client and server connection messages to make sure server is connected 
        try {
            connectionMessage = (String) in.readObject();
            out.writeObject("Client Connected");
            out.flush();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(connectionMessage);
        //Ask user to input the phrase the other player will attempt to guess
        char[] charPhrase = PhraseJOP.getPhrase();
        StringBuilder strPhrase = new StringBuilder();
        //Create a string from inout to be displayed
        for (char c : charPhrase){
            strPhrase.append(c);
        }
        Phrase phrase = new Phrase(charPhrase);
        display.setYourPhrase(strPhrase.toString());
        //Send the phrase to the server
        sendPhrase(phrase);
        //Wait until the other player has set their phrase before continuing
        try {
            String phraseSet = (String) in.readObject();
            System.out.println(phraseSet);
            //Activate check phrase button
            display.activateButton();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    

        // Process all messages from server, according to the protocol.
        while (true)
        {
            try {
                //Wait for a ServerResponse
                ServerResponse response = (ServerResponse) in.readObject();
                //Response contains message about checked phrase
                if (response.containsMessage()) {
                    String[] message = response.getMessage();
                    System.out.println(Arrays.toString(message));
                    display.updateCurrentTurn(message);
                    //If the lengh is only 1, the player has guessed the phrase and does not need to check more phrases
                    if(message.length == 1){
                        display.disableButton();
                    }
		}
                //Response contains a guessed phrase from the opponent
                if (response.containsPhrase()) {
                    Phrase opponentPhrase = response.getPhrase();
                    display.updateOpponentGuesses(new String(opponentPhrase.getPhrase()));
		}
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            }

        }
               
//               out.close();
//               in.close();
//               socket.close();
    }
    
    /**
     * Send a phrase to the server
     * @param phrase 
     */
    public void sendPhrase(Phrase phrase){
        try {
            out.writeObject(phrase);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
