/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MyDB {
    private final String Url = "jdbc:mysql://localhost:3306/pidev";
    private final String user="root";
    private final String password="";
    private Connection connection;
    static MyDB instance;
    private MyDB(){
        
        try {
            connection = DriverManager.getConnection(Url, user, password);
                   System.out.println("connexion établie");
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static MyDB getInstance(){
        if(instance==null)
            instance= new MyDB();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
