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
        try {
            String[] serverInformation = getServerAddress();
            String strIP = serverInformation[0];
            int intPort = Integer.parseInt(serverInformation[1]);
            this.strHost = strIP;
            this.intPort = intPort;
            run(strIP, intPort);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Client(int intPort){
        strHost = "127.0.0.1";
        this.intPort = intPort;
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
    

        // Process all messages from server, according to the protocol.
        while (true)
        {
        	try {
				String[] message = (String[]) in.readObject();
				System.out.println(Arrays.toString(message));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

        }
    }
}
