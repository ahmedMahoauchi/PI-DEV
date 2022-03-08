/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Employe;
import utiles.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Packard bell
 */
public class ServiceEmploye {
    Connection cnx= Maconnexion.getInstance().getCnx();
    private static ServiceEmploye instance;
    
    
    
    public static ServiceEmploye getInstance(){
        if(instance==null)
            instance=new ServiceEmploye();
        return instance;
    }
    public void add(Employe c) throws SQLException{
        
       
        
     String req= "INSERT INTO `employes` (NomEmploye,DateEmploye,NumEmploye,SalaireEmploye) VALUES ('"
                + c.getNomEmploye()+"', '"
                + c.getDateEmploye()+"','"
                + c.getNumEmploye()+"','"
                + c.getSalaireEmploye()+"')";
        Statement stm = cnx.createStatement();
        stm.executeUpdate(req);
    }
     public ObservableList<Employe> getAll() throws SQLException {
     ObservableList<Employe> result =  FXCollections.observableArrayList();
     String req = "SELECT * FROM `employes`";
     Statement stm = cnx.createStatement();
     
     ResultSet rst = stm.executeQuery(req);
     
     while(rst.next()){
         Employe C = new Employe();

         C.setIdEmploye(rst.getInt("idEmploye"));
         C.setNomEmploye(rst.getString("NomEmploye"));
         C.setDateEmploye(rst.getString("DateEmploye"));
         C.setNumEmploye(rst.getInt("NumEmploye"));
         C.setSalaireEmploye(rst.getInt("SalaireEmploye"));
         result.add(C);
     }

     return result;
    } 
    public void updateEmploye(Employe C) throws SQLException{
        String req = "UPDATE `employes` SET NomEmploye = ?, DateEmploye = ?, NumEmploye= ?,SalaireEmploye=? WHERE IdEmploye= ?" ;
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setString(1, C.getNomEmploye());
         pst.setString(2, C.getDateEmploye());
         pst.setInt(3, C.getNumEmploye());
         pst.setInt(4, C.getSalaireEmploye());
         pst.setInt(5, C.getIdEmploye());
         
         pst.executeUpdate();
    }
   public void delete(String IdEmploye) throws SQLException{
        String req= "DELETE FROM `employes` WHERE  IdEmploye= ?" ;
         PreparedStatement pst = cnx.prepareStatement(req);
         pst.setString(1, IdEmploye);
         pst.executeUpdate();
        
    }



     
     
}
