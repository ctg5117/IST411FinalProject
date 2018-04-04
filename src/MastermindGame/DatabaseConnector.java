/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.io.IOException;
import java.nio.file.Files;
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
        if(!testConnection()){
            createTable();
        }
    }
    
    public void connect(){
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://istdata.bk.psu.edu:3306/bvs5441", "bvs5441", "berks2833");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection(){
        try {
            if(!myConnection.isClosed()){
                myConnection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    public ResultSet getPastGames(){
        try {
            PreparedStatement s = myConnection.prepareStatement("select phrase,turns from pastgames");
            return s.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void createTable(){
        String strQuery = "";
        try {
            //TODO: This needs to be modified still but will work if the path is set to the correct path
            strQuery = new String(Files.readAllBytes(Paths.get("X:\\My Documents\\NetBeansProjects\\IST411FinalProject\\scripts\\pastgames.sql")));
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
