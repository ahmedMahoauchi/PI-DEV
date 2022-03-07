/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Programme;
import util.MyDB;
import java.sql.Connection;
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
public class ServiceProgramme implements Iservice<Programme> {
Connection cnx ;

    public ServiceProgramme() {
    cnx=MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Programme p) {
    try {
        /*String requete ="INSERT INTO programme (idUser,nomProgramme,descriptionProgramme,niveauProgramme,genreProgramme,typeProgramme,imageProgramme)"
                +"VALUES ('1','test','test','1','1','muscilation','c:gtgeg')";
        Statement st=cnx.createStatement();
        st.executeUpdate(requete);*/
        
        String requete ="INSERT INTO programme (idUser,nomProgramme,descriptionProgramme,niveauProgramme,genreProgramme,typeProgramme,imageProgramme)"
                +"VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst=cnx.prepareStatement(requete);
        pst.setInt(1, p.getIdUser());
        pst.setString(2, p.getNomProgramme());
        pst.setString(3, p.getDescriptionProgramme());
        pst.setInt(4, p.getNiveauProgramme());
        pst.setInt(5, p.getGenreProgramme());
        pst.setString(6, p.getTypeProgramme());
        pst.setString(7, p.getImageProgramme());
        pst.executeUpdate();
        System.out.println("Produit ajouté avec succès");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
      


    }

    @Override
    public List<Programme> afficher() {
List<Programme> MyList = new ArrayList<>();
        try{
            String requete2= "SELECT * FROM programme";
            Statement st= cnx.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                Programme p = new Programme();
                p.setIdProgamme(rs.getInt(1));
                p.setIdUser(rs.getInt(2));
                p.setNomProgramme(rs.getString(3));
                p.setDescriptionProgramme(rs.getString(4));
                p.setNiveauProgramme(rs.getInt(5));
                p.setGenreProgramme(rs.getInt(6));
                p.setTypeProgramme(rs.getString(7));
                p.setImageProgramme(rs.getString("imageProgramme"));
                
                MyList.add(p);
            }
            System.out.println("All Programmes extracted successfully");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return MyList;
    }

    @Override
    public void modifier(Programme p) {
  try{
            String requete3 = "update programme set idUser=?, nomProgramme=?, descriptionProgramme=?, niveauProgramme=?, genreProgramme=?, typeProgramme=?, imageProgramme=?"
                    +"where idProgramme =?";
            PreparedStatement pst=cnx.prepareStatement(requete3);
        pst.setInt(1, p.getIdUser());
        pst.setString(2, p.getNomProgramme());
        pst.setString(3, p.getDescriptionProgramme());
        pst.setInt(4, p.getNiveauProgramme());
        pst.setInt(5, p.getGenreProgramme());
        pst.setString(6, p.getTypeProgramme());
        pst.setString(7, p.getImageProgramme());
        pst.setInt(8, p.getIdProgamme());
        pst.executeUpdate();
            System.out.println("programme modifié avec succès");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
try{
            String requete4="delete from programme where idProgramme=?";
            PreparedStatement pst= cnx.prepareStatement(requete4);
            pst.setInt(1,id);
            
            pst.executeUpdate();
            System.out.println("Product deleted successfully");
            
        }catch(SQLException ex1){
            System.err.println(ex1.getMessage());
        }
    }
    }
    

