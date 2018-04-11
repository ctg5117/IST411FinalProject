/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author ctg5117
 */
public class Client
{
    //Use run from this code http://cs.lmu.edu/~ray/notes/javanetexamples/
    
    //Open main menu
    
    //Select Join game -> get ip of server -> server connected -> get 
    
    //
    
    
    BufferedReader in;
    PrintWriter out;
    
    private String getServerAddress()
    {
        return JOptionPane.showInputDialog(null, "Enter IP Address of the Server:", "Welcome to the Chatter", JOptionPane.QUESTION_MESSAGE);
    }
    
    private void run() throws IOException
    {

    // Make connection and initialize streams
    String serverAddress = getServerAddress();
    Socket socket = new Socket(serverAddress, 9001);
    
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true)
        {


        }
    }
}
