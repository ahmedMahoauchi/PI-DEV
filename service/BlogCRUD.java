/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

import entities.Blog;
import entities.Rubrique;
import utils.myconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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

public class BlogCRUD  {
    
    Connection cnx2;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public BlogCRUD() {
        cnx2 = myconnection.getInstance().getCnx();
    }
    /*
     @Override
     
     public void insert(Blog b) {
        String req = "insert into blog (id,titre,description,photo,votes,date,id_rubrique,id_user) values ('" + b.getId() + "','" + b.getTitre() + "','" + b.getDescription()+ "','" + b.getPhoto()+ "','" + b.getVotes()+ "','" + b.getDate()+ "','" + b.getId_rubrique()+ "','" + b.getId_user()+ "')";
        
        try {
            ste = cnx2.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(BlogCRUD.class.getName()).log(Level.SEVERE, null, ex);
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
    public void ajouter (Blog b){
        try {
            String requete2= "INSERT INTO blog (titre,description,photo,votes,date,id_rubrique,id_user)"
                    + "VALUES (?,?,?,0,?, (SELECT id FROM rubrique WHERE id=2),(SELECT idUser FROM user WHERE idUser=4))";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            
            pst.setString(1, b.getTitre());
            pst.setString(2, b.getDescription());
            pst.setString(3, b.getPhoto());
            pst.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
            
            pst.executeUpdate();
                    System.out.println("votre blog est ajoutée");
        } catch (SQLException ex) {
        System.err.println(ex.getMessage());}
    }
    
    
    public void ajouter2 (Blog b){
        try {
            String requete2= "INSERT INTO blog (titre,description,photo,votes,date,id_rubrique,id_user)"
                    + "VALUES (?,?,?,0,?,?,(SELECT idUser FROM user WHERE idUser=4))";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            
            pst.setString(1, b.getTitre());
            pst.setString(2, b.getDescription());
            pst.setString(3, b.getPhoto());
            pst.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
            pst.setInt(5, b.getId_rubrique());
            
            pst.executeUpdate();
                    
                    Notifications notificationBuilder =Notifications.create()
                            .title("Alert").text("votre blog est ajoutée avec succes!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                            .position(Pos.CENTER_LEFT)
                            .onAction(new EventHandler<ActionEvent>(){
                            public void handle (ActionEvent event)
                            {
                            System.out.println("votre blog est ajoutée avec succes!");                          
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

    public void modifier(Blog b) {
        try {
            
            String req = "UPDATE blog SET titre=?, description=?, photo=? WHERE id=?";
                      PreparedStatement pst = cnx2.prepareStatement(req);
           
            pst.setString(1, b.getTitre());
            pst.setString(2, b.getDescription());
            pst.setString(3, b.getPhoto());
            pst.setInt(4, b.getId());
            pst.executeUpdate();
            System.out.println("votre blog est modifié");
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
     public void modifier2(Blog b) {
        try {
            
            String req = "UPDATE blog SET titre=?, description=?, photo=?, id_rubrique=? WHERE id=?";
                      PreparedStatement pst = cnx2.prepareStatement(req);
           
            pst.setString(1, b.getTitre());
            pst.setString(2, b.getDescription());
            pst.setString(3, b.getPhoto());
            pst.setInt(4, b.getId_rubrique());
            pst.setInt(5, b.getId());
            pst.executeUpdate();
            System.out.println("votre blog est modifié");
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public void supprimer(int id) {
        String req = "DELETE FROM blog WHERE id=?";
        try {
            PreparedStatement pst = cnx2.prepareStatement(req);
            pst = cnx2.prepareStatement(req);
            //pst.setInt(1, b.getId());
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException ex) {
              System.err.println(ex.getMessage());

        }
    }

    public List<Blog> afficher() {
        String req = "select * from blog";
        List<Blog> list = new ArrayList<>();
        try {
            ste = cnx2.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Blog(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getString("photo"), rs.getInt("votes"), rs.getTimestamp("date"), rs.getInt("id_rubrique"), rs.getInt("id_user")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 
    
    
    
    
    
 public Blog readById(int id) {
        String req = "select * from blog where id="+id;
        Blog b = new Blog();

        try {
            ste = cnx2.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                b.setId(rs.getInt("id"));
                b.setTitre(rs.getString("titre"));
                b.setDescription(rs.getString("description"));
                b.setPhoto(rs.getString("photo"));
                b.setVotes(rs.getInt("votes"));
                b.setDate(rs.getTimestamp("date"));
                b.setId_rubrique(rs.getInt("id_rubrique"));
                b.setId_user(rs.getInt("id_user"));
               
                
                
            }
        } catch (SQLException ex) {
              System.err.println(ex.getMessage());
        }
        return b;
    }


}
