/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Rubrique;
import utils.myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

/**
 *
 * @author oussama
 */

public class RubriqueCRUD {
    
    Connection cnx2;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public RubriqueCRUD() {
        cnx2 = myconnection.getInstance().getCnx();
    }
     
     
    /* @Override
    public void insert(Rubrique r) {
        String req = "insert into rubrique (id,titre) values ('" + r.getId() + "','" + r.getTitre() + "')";
        
        try {
            
            ste = cnx2.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        
    }
     */
     
    /*
    public void ajouterRubrique(){
        try {
            String requete = "INSERT INTO rubrique (titre) "
                    + "VALUES ('Advice/Tips')";
            Statement st = new MyConnection().getCnx().createStatement();
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Rubrique ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    */
    public void ajouter (Rubrique r){
        try {
            String requete2 = "INSERT INTO rubrique (titre)"
                    + "VALUES (?)";
            //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, r.getTitre());
            pst.executeUpdate();
                    Notifications notificationBuilder =Notifications.create()
                            .title("Alert").text("votre rubrique est ajoutée avec succes!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                            .position(Pos.CENTER_LEFT)
                            .onAction(new EventHandler<ActionEvent>(){
                            public void handle (ActionEvent event)
                            {
                            System.out.println("votre rubrique est ajoutée avec succes!");                          
                            }
                            
                            });
                    notificationBuilder.darkStyle();
                    notificationBuilder.show();

        } catch (SQLException ex) {
        System.err.println(ex.getMessage());}
    }
    
    /* public List<Rubrique> afficherRubriques(){
         List<Rubrique> myList = new ArrayList<>();
        try {      
        String requete3 = "SELECT * FROM rubrique";
        //Statement st = new MyConnection().getCnx().createStatement();
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(requete3);
        while(rs.next())  {
        Rubrique r = new Rubrique();
        r.setId(rs.getInt(1));
        r.setTitre(rs.getString("titre"));
        myList.add(r);
        }
                
    } catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
         return myList;
}
 */

    public void modifier(Rubrique r) {
        try {
            
            String req = "UPDATE rubrique SET titre=? WHERE id=?";
                      PreparedStatement pst = cnx2.prepareStatement(req);
            pst.setString(1, r.getTitre());
              pst.setInt(2, r.getId());
            pst.executeUpdate();
            System.out.println("votre rubrique est modifié");
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public void supprimer(int id) {
        String req = "DELETE FROM rubrique WHERE id=?";
        try {
            PreparedStatement pst = cnx2.prepareStatement(req);
            pst = cnx2.prepareStatement(req);
            //pst.setInt(1, r.getId());
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
              System.err.println(ex.getMessage());

        }
    }

    public List<Rubrique> afficher() {
        String req = "select * from rubrique";
        List<Rubrique> list = new ArrayList<>();
        try {
            ste = cnx2.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Rubrique(rs.getInt("id"), rs.getString("titre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 
 public Rubrique readById(int id) {
        String req = "select * from rubrique where id="+id;
        Rubrique r = new Rubrique();

        try {
            ste = cnx2.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                r.setId(rs.getInt("id"));
                r.setTitre(rs.getString("titre"));
               
                
                
            }
        } catch (SQLException ex) {
              System.err.println(ex.getMessage());
        }
        return r;
    }

    public void ajouter2(Rubrique t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modifier2(Rubrique t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }






}
