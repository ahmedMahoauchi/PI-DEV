package service;

import entities.Commande;
import utils.myconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandeService {

    public void creerCommande(Commande commande){
        try {
            String requete = "INSERT INTO `commande`" +
                "(`idUser`, `prixCommande`, `isPayed`, `createdDTM`) " +
                "VALUES (?,?,?,?)";
            PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete);
            pst.setInt(1,commande.getIdUser());
            pst.setDouble(2,commande.getPrixCommande());
            pst.setInt(3,commande.getIsPayed());
            pst.setString(4,commande.getCreatedDTM());
            pst.executeUpdate();
            System.out.println("Commande ajoutée...");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Commande> afficherCommandes(){
        List<Commande> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commande";
            Statement st = new myconnection().getCnx().createStatement();
            ResultSet resultSet = st.executeQuery(requete);

            Commande commande = new Commande();
            while (resultSet.next()){
                commande.setIdCommande(resultSet.getInt(1));
                commande.setIdUser(resultSet.getInt(2));
                commande.setPrixCommande(resultSet.getFloat(3));
                commande.setIsPayed(resultSet.getInt(4));
                commande.setCreatedDTM(resultSet.getString(5));
                myList.add(commande);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }

    public void supprimerCommande(int id){
        try {
            String requete = "DELETE FROM commande WHERE idCommande ="+id;
            PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commande Supprimée ...");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierCommande(int idCommande,Commande commande) {
        try {
            String requete5 = "UPDATE `commande` " +
                    "SET `prixCommande`=?,`isPayed`=? " +
                    "WHERE idCommande=" + idCommande;
            PreparedStatement pst = new myconnection().getCnx().prepareStatement(requete5);
            pst.setFloat(1,commande.getPrixCommande());
            pst.setInt(2,commande.getIsPayed());
            pst.executeUpdate();
            System.out.println("Commande modifiée ...");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Commande getCommandeById(int idCommande){
        Commande commande = new Commande();
        try {
            String requete = "SELECT * FROM commande WHERE idCommande="+idCommande;
            Statement st = new myconnection().getCnx().createStatement();
            ResultSet resultSet = st.executeQuery(requete);

            if(resultSet != null){

                commande.setIdCommande(resultSet.getInt(1));
                commande.setIdUser(resultSet.getInt(2));
                commande.setPrixCommande(resultSet.getFloat(3));
                commande.setIsPayed(resultSet.getInt(4));
                commande.setCreatedDTM(resultSet.getString(5));
            }
            else {
                commande = null;

            }
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }

        return commande;

    }



}
