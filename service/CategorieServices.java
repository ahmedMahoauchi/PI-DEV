package service;

import entities.Categorie;
import utils.myconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieServices {


    public void ajouterCategorie(){

        try {
            String requete = "INSERT INTO `categorie`(`nomCategorie`, `imageCategorie`, `createdDTM`, `createdBy`) " +
                    "VALUES ('tasses','aaaaaaa','1999/03/05',12)";
            Statement st = new myconnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Categorie Ajoutée ...");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterCategorie2(Categorie categorie){

        try {
            String requete2 = "INSERT INTO `categorie`" +
                    "(`nomCategorie`, `createdDTM`, `createdBy`) " +
                    "VALUES (?,?,?)" ;
            PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete2);

            pst.setString(1,categorie.getNomCategorie());
            pst.setString(2,categorie.getCreatedDTM());
            pst.setInt(3,categorie.getCreatedBy());

            pst.executeUpdate();

            System.out.println("Categorie2 Ajoutée ...");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Categorie> afficherCategories(){

        List<Categorie> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM categorie";
            Statement st = new myconnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()){
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(rs.getInt(1));
                categorie.setNomCategorie(rs.getString(2));
                categorie.setCreatedDTM(rs.getString(3));
                categorie.setCreatedBy(rs.getInt(4));
                myList.add(categorie);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }

    public void supprimerCategorie(int idcategorie){
        try {
            String requete4 = "DELETE FROM categorie WHERE idCategorie ="+idcategorie;
            Statement st =  new myconnection().getCnx().createStatement();
            st.executeUpdate(requete4);
            System.out.println("Categorie Supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierCategorie(int idcategorie,Categorie categorie) {
        try {
            String requete5 = "UPDATE `categorie` " +
                    "SET `nomCategorie`=?,`imageCategorie`=?" +
                    "WHERE idCategorie =" + idcategorie;
            PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete5);
            pst.setString(1,categorie.getNomCategorie());
            pst.executeUpdate();
            System.out.println("Categorie modifiée ...");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
