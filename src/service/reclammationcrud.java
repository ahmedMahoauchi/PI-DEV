package service;


import entities.reclammation;
import utils.myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author client
 */
public class reclammationcrud {
    Connection cnx2;
    public reclammationcrud()
{
cnx2 = myconnection.getInstance().getCnx();
}
     private static reclammationcrud instance;
     private String idReclamation;
    private String idUser;
    private String themeReclamation;
    private String description;
      
     public static reclammationcrud getInstance(){
        if(instance==null)
            instance=new reclammationcrud();
        return instance;
    }
     
    public void ajouterreclam(reclammation r) throws SQLException
    {
       
            String requete ="INSERT INTO reclamation (idUser,themeReclamation,description)"
                    + "VALUES (?,?,?)" ;
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1,r.getIdUser());
            pst.setString(2,r.getThemeReclamation());
            pst.setString(3,r.getDescription());
            pst.execute();
           
      
        
      
                
    }
      public ObservableList<reclammation> afficherrecla() throws SQLException
        {
             ObservableList<reclammation> result = FXCollections.observableArrayList();
            String requete2 = "SELECT * FROM reclamation";
            
      
           
            Statement st = cnx2.createStatement(); 
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next())
            {
                reclammation r = new reclammation();
                r.setIdReclamation(rs.getString("idReclamation"));
                r.setIdUser(rs.getString("idUser"));
                r.setThemeReclamation(rs.getString("themeReclamation"));
                r.setDescription(rs.getString("description"));
                result.add(r);
                
            }
       
        return result;
             }
       public void supprimerrecla(String reference) throws SQLException
 {
     String requete3 = "DELETE FROM reclamation WHERE description= ?";
      
            
 PreparedStatement pst = cnx2.prepareStatement(requete3);
  pst.setString(1 , reference);
 pst.executeUpdate();
            
       
 }
       
         public void modifierrecla(reclammation r) throws SQLException
{
       
            String requete3 = "UPDATE reclamation SET themeReclamation = ?,description= ? WHERE idReclamation=? ";
            
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setString(1,r.getIdUser());
            pst.setString(2,r.getThemeReclamation());
            pst.setString(3,r.getDescription());
             pst.setString(4,r.getIdReclamation());
          pst.executeUpdate();



}
}     
