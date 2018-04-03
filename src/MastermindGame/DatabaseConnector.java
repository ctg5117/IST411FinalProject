/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

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
    
    
    //The rest of this class is for testing purposes
    public void testConnection(){
        try {
            PreparedStatement s = myConnection.prepareStatement("select * from test");
            ResultSet rs = s.executeQuery();
            rs.next();
            System.out.println(rs.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        DatabaseConnector testConnector = new DatabaseConnector();
        testConnector.connect();
        testConnector.testConnection();
        testConnector.closeConnection();
    }
    
}
