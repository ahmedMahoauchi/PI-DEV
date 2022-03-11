/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entities.Planning;
import utils.myconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS
 */
public class ServicePlanning  {
Connection cnx33 ;
    public ServicePlanning() {
         cnx33= myconnection.getInstance().getCnx();
    }

    
    public void ajouter(Planning p) {
try {
        String requete ="INSERT INTO planning (idProgramme,nomPlanning,descriptionPlanning,datePlanning,lieuPlanning)"
                +"VALUES (?,?,?,?,?)";
        PreparedStatement pst=cnx33.prepareStatement(requete);
        pst.setInt(1, p.getIdProgramme());
        pst.setString(2, p.getNomPlanning());
        pst.setString(3, p.getDescriptionPlanning());
        pst.setDate(4, (Date) p.getDatePlanning());
        pst.setString(5, p.getLieuPlanning());
        
        pst.executeUpdate();
        System.out.println("Plannig ajouté avec succès");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
      
    }
   
    public List<Planning> afficher() {
List<Planning> MyList2 = new ArrayList<>();
        try{
            String requete2= "SELECT p.idPlanning,p.idProgramme,p.nomPlanning,p.descriptionPlanning,p.datePlanning,p.lieuPlanning,pl.nomProgramme FROM planning p join programme pl on p.idProgramme=pl.idProgramme ";
            Statement st= cnx33.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                Planning pl = new Planning();
                pl.setIdPlanning(rs.getInt(1));
                pl.setIdProgramme(rs.getInt(2));
                 pl.setNomPlanning(rs.getString(3));
                pl.setDescriptionPlanning(rs.getString(4));
                pl.setDatePlanning(rs.getDate(5));
                pl.setLieuPlanning(rs.getString("lieuPlanning"));
                pl.setNomProgramme(rs.getString("nomProgramme"));
                
                
                MyList2.add(pl);
            }
            System.out.println("All Planning extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return MyList2;
        }

    
    public void modifier(Planning p) {
try{
            String requete3 = "update planning set idProgramme =?, nomPlanning=?, descriptionPlanning=?, datePlanning=?, lieuPlanning=? where idPlanning =?";
                    
            PreparedStatement pst=cnx33.prepareStatement(requete3);
        pst.setInt(1, p.getIdProgramme());
        pst.setString(2, p.getNomPlanning());
        pst.setString(3, p.getDescriptionPlanning());
        pst.setDate(4, (Date) p.getDatePlanning());
        pst.setString(5, p.getLieuPlanning());
        pst.setInt(6, p.getIdPlanning());
        pst.executeUpdate();
            System.out.println("planning modifié avec succès");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    
    public void supprimer(int id) {
try{
            String requete4="delete from planning where idPlanning=?";
            PreparedStatement pst= cnx33.prepareStatement(requete4);
            pst.setInt(1,id);
            
            pst.executeUpdate();
            System.out.println("planning deleted successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }
    }

    
    

