/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brady
 */
public class DatabaseConnector {
    
    private Connection myConnection;
    
    public DatabaseConnector(){
        connect();
        //Check to make sure database exists and create the table if it does not exist
        if(!testConnection()){
            createTable();
        }
    }
    
    /**
     * Opens a connection to the database
     */
    public void connect(){
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://istdata.bk.psu.edu:3306/bvs5441", "bvs5441", "berks2833");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Closes the connection
     */
    public void closeConnection(){
        try {
            if(!myConnection.isClosed()){
                myConnection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add a game to the database
     * @param strInIP IP address of player
     * @param strInPhrase Phrase used in the game
     * @param intInTurnCount Turns it took to guess phrase
     */
    public void addGame(String strInIP, String strInPhrase, int intInTurnCount){
        try {
            PreparedStatement s = myConnection.prepareStatement("insert into pastgames (IP, phrase, turns) values (?,?,?)");
            s.setString(1, strInIP);
            s.setString(2, strInPhrase);
            s.setInt(3, intInTurnCount);
            s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Get the list of past games from the database
     * @return ResultSet containing the phrases and number of turns
     */
    public ResultSet getPastGames(){
        try {
            PreparedStatement s = myConnection.prepareStatement("select phrase,turns from pastgames");
            return s.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Runs an SQL script to create the table in the database and add a test record
     */
    private void createTable(){
        String strQuery = "";
        try {
            Path path = FileSystems.getDefault().getPath("scripts\\pastgames.sql");
            strQuery = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getLocalizedMessage());
            return;
        }
        try {
            PreparedStatement s = myConnection.prepareStatement(strQuery);
            s.executeUpdate();
            PreparedStatement s1 = myConnection.prepareStatement("insert into pastgames values (1, '127.0.0.1', 'test', 1)");
            s1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //The rest of this class is for testing purposes
    public boolean testConnection(){
        try {
            PreparedStatement s = myConnection.prepareStatement("select * from pastgames");
            ResultSet rs = s.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString(3));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) {
        DatabaseConnector testConnector = new DatabaseConnector();
        testConnector.closeConnection();
    }
    
}
