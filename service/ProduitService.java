package service;

import entities.Produit;
import utils.myconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
    public void ajouterProduit(Produit produit){

        try {
            String requete2 = "INSERT INTO `produit`" +
                    "(`idCategorie`, `nomProduit`, `descriptionProduit`, `prixProduit`, `quantiteProduit`, `imageProduit`, `createdDTM`, `createdBy`) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete2);

            pst.setInt(1,produit.getIdCategorie());
            pst.setString(2,produit.getNomProduit());
            pst.setString(3,produit.getDescriptionProduit());
            pst.setDouble(4,produit.getPrixProduit());
            pst.setInt(5,produit.getQuantiteProduit());
            pst.setString(6,produit.getImageProduit());
            pst.setString(7,produit.getCreatedDTM());
            pst.setInt(8,produit.getCreatedBy());

            pst.executeUpdate();

            System.out.println("Produit Ajouté ...");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public List<Produit> afficherProduits(){

        List<Produit> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM produit";
            Statement st = new myconnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()){
                Produit produit = new Produit();
                produit.setIndProduit(rs.getInt(1));
                produit.setIdCategorie(rs.getInt(2));
                produit.setNomProduit(rs.getString(3));
                produit.setDescriptionProduit(rs.getString(4));
                produit.setPrixProduit(rs.getFloat(5));
                produit.setQuantiteProduit(rs.getInt(6));
                produit.setImageProduit(rs.getString(7));
                produit.setCreatedDTM(rs.getString(8));
                produit.setCreatedBy(rs.getInt(9));
                myList.add(produit);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }


    public void supprimerProduit(int idcategorie){
        try {
            String requete4 = "DELETE FROM produit WHERE idProduit ="+idcategorie;
            Statement st =  new myconnection().getCnx().createStatement();
            st.executeUpdate(requete4);
            System.out.println("Produit Supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }


  public void modifierProduit(int idProduit,Produit produit) {
      try {
          String requete5 = "UPDATE `produit` " +
                  "SET `idCategorie`=?,`nomProduit`=?," +
                  "`descriptionProduit`=?,`prixProduit`=?,`quantiteProduit`=?" +
                  " WHERE idProduit =" + idProduit;
          PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete5);
          pst.setInt(1,produit.getIdCategorie());
          pst.setString(2,produit.getNomProduit());
          pst.setString(3,produit.getDescriptionProduit());
          pst.setDouble(4,produit.getPrixProduit());
          pst.setInt(5,produit.getQuantiteProduit());
          pst.executeUpdate();
          System.out.println("Categorie modifiée ...");
      } catch (SQLException ex) {
          System.err.println(ex.getMessage());
      }
  }



    public void modifierProduit1(int idProduit,int qy) {
        try {
            String requete5 = "UPDATE produit SET quantiteProduit = (quantiteProduit - "+qy+") WHERE idProduit = "+idProduit;
            PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete5);
            pst.executeUpdate();
            System.out.println("produit modifié ...");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }







}
