/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Conge;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utiles.Maconnexion;

/**
 *
 * @author Packard bell
 */
public class ServiceConge {
    
    Connection cnx= Maconnexion.getInstance().getCnx();
    private static ServiceConge instance;
    
    
    private int IdConge;
    private int IdEmploye;
    
    private String DebutConge;
    private String FinConge;
    
    private String TypeConge;
    
    
    
    public static ServiceConge getInstance(){
        if(instance==null)
            instance=new ServiceConge();
        return instance;
    }
    public void add(Conge p  ) throws SQLException{
        
        String req="insert into conge (TypeConge,IdEmploye,DebutConge,FinConge) values(?,?,?,?)";
       
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, p.getTypeConge());
            pst.setInt(2, p.getIdEmploye());
            pst.setString(3, p.getDebutConge());
            pst.setString(4, p.getFinConge());
            pst.execute();
         
       
        
         }
     public ObservableList<Conge> getAll() throws SQLException {
    ObservableList<Conge> result = FXCollections.observableArrayList();
     String req = "SELECT * FROM `conge`";
     Statement stm = cnx.createStatement();
     
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Conge p = new Conge();

         
           p.setTypeConge(rst.getString("TypeConge"));
         p.setIdEmploye(rst.getInt("IdEmploye"));
         p.setDebutConge(rst.getString("DebutConge"));
           p.setFinConge(rst.getString("FinConge"));
         p.setIdConge(rst.getInt("IdConge"));
         result.add(p);
     }

     return result;
    } 
public void update(Conge p) throws SQLException{
    String req="UPDATE `conge` SET TypeConge = ?, IdEmploye =?, DebutConge =?, FinConge =? WHERE IdConge=?";
    PreparedStatement pst =cnx.prepareStatement(req);
    
    pst.setString(1, p.getTypeConge());
    pst.setInt(2, p.getIdEmploye());
    pst.setString(3, p.getDebutConge());
    pst.setString(4,p.getFinConge());
    pst.setInt(5, p.getIdConge());
    pst.executeUpdate();
    
}
public void deleteConge(int id)throws SQLException{
    

    String req= "DELETE FROM `conge` WHERE IdConge= ?";
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setInt(1, id);
         pst.executeUpdate();
       
}
}
    
     
