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
    
    public Client(){
        String[] serverInformation = getServerAddress();
        String strIP = serverInformation[0];
        int intPort = Integer.parseInt(serverInformation[1]);
        this.strHost = strIP;
        this.intPort = intPort;
    }
    
    public Client(int intPort){
        strHost = "127.0.0.1";
        this.intPort = intPort;
    }
    
    public void setDisplay(JPGame display){
        this.display = display;
    }
    
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
    
    @Override
    public void run(){
        try {
            run(strHost, intPort);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void run(String strIP, int intPort) throws IOException
    {

    // Make connection and initialize streams
    Socket socket = new Socket(strIP, intPort);
    out = new ObjectOutputStream(socket.getOutputStream());
    out.flush();
    in = new ObjectInputStream(socket.getInputStream());
    String connectionMessage = null;
        try {
            connectionMessage = (String) in.readObject();
            out.writeObject("Client Connected");
            out.flush();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println(connectionMessage);

        char[] charPhrase = PhraseJOP.getPhrase();
        StringBuilder strPhrase = new StringBuilder();
        for (char c : charPhrase){
            strPhrase.append(c);
        }
        Phrase phrase = new Phrase(charPhrase);
        display.setYourPhrase(strPhrase.toString());
        sendPhrase(phrase);
        try {
            String phraseSet = (String) in.readObject();
            System.out.println(phraseSet);
            display.activateButton();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    

        // Process all messages from server, according to the protocol.
        while (true)
        {
        	try {
				ServerResponse response = (ServerResponse) in.readObject();
				if (response.containsMessage()) {
					String[] message = response.getMessage();
					System.out.println(Arrays.toString(message));
                    display.updateCurrentTurn(message);
                    if(message.length == 1){
                        break;
                    }
				}
				if (response.containsPhrase()) {
					Phrase opponentPhrase = response.getPhrase();
					display.updateOpponentGuesses(new String(opponentPhrase.getPhrase()));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

        }
               display.disableButton();
               out.close();
               in.close();
               socket.close();
    }
    
    public void sendPhrase(Phrase phrase){
        try {
            out.writeObject(phrase);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
