/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import entities.abonnement;
import utils.myconnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author client
 */
public class abonnementcrud {
    Connection cnx2;
    public abonnementcrud()
{
cnx2 = myconnection.getInstance().getCnx();
}
   
  private static abonnementcrud instance;
   private int idAbonnement;
    private String typeAbonnement;
    private String descriptionAbonnemment;
    private String prixAbonnemment;
      
  public static abonnementcrud getInstance(){
        if(instance==null)
            instance=new abonnementcrud();
        return instance;
    }
  
  
      public void ajouterabonnement(abonnement b) throws SQLException{
            String requete = "INSERT INTO `abonnement` (typeAbonnement,descriptionAbonnement,prixAbonnement)"
                    + "VALUES  (?,?,?) ";
        PreparedStatement pst = cnx2.prepareStatement(requete);
       pst.setString(1, b.getTypeAbonnement());
       pst.setString(2, b.getDescriptionAbonnemment());
       pst.setString(3, b.getPrixAbonnemment());
     
       pst.execute();
       
       
    }
     
      public ObservableList<abonnement> afficherabonnements() throws SQLException{ 
          
         ObservableList<abonnement> result = FXCollections.observableArrayList();
            String requete2 = "SELECT * FROM abonnement";
       
         
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete2);  
            while(rs.next()){
                abonnement b = new abonnement();
                b.setIdAbonnement(rs.getString("idAbonnement"));
                b.setTypeAbonnement(rs.getString("typeAbonnement"));
                b.setDescriptionAbonnemment(rs.getString("descriptionAbonnement"));
                b.setPrixAbonnemment(rs.getString("prixAbonnement"));
                 result.add(b);
            }
           
        
         return result;
    }
     public void AnnulerAbonnement(String reference) throws SQLException
 {
     String requete3 = "DELETE FROM `abonnement` WHERE descriptionAbonnement= ?";
       
            
            
           PreparedStatement st = cnx2.prepareStatement(requete3);
            st.setString(1, reference);
            st.executeUpdate();
       
 }
     public void modifierAbonnement(abonnement b) throws SQLException
{
       
            
            
            String requete4 = "UPDATE `abonnement` SET typeAbonnement = ?,descriptionAbonnement = ?,prixAbonnement = ?   WHERE idAbonnement=? ";
            
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.setString(1, b.getTypeAbonnement());
       pst.setString(2, b.getDescriptionAbonnemment());
       pst.setString(3, b.getPrixAbonnemment());
       pst.setString(4, b.getIdAbonnement());
          pst.executeUpdate();


}
    
}


