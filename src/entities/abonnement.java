/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author client
 */
public class abonnement {
    private String idAbonnement;
    private String typeAbonnement;
    private String descriptionAbonnemment;
    private String prixAbonnemment;
     public abonnement(){
        
    }

    public abonnement(String idAbonnement, String typeAbonnement, String descriptionAbonnemment, String prixAbonnemment) {
        this.idAbonnement = idAbonnement;
        this.typeAbonnement = typeAbonnement;
        this.descriptionAbonnemment = descriptionAbonnemment;
        this.prixAbonnemment = prixAbonnemment;
    }

    public abonnement(String typeAbonnement, String descriptionAbonnemment, String prixAbonnemment) {
        this.typeAbonnement = typeAbonnement;
        this.descriptionAbonnemment = descriptionAbonnemment;
        this.prixAbonnemment = prixAbonnemment;
    }

    public String getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(String idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public String getTypeAbonnement() {
        return typeAbonnement;
    }

    public void setTypeAbonnement(String typeAbonnement) {
        this.typeAbonnement = typeAbonnement;
    }

    public String getDescriptionAbonnemment() {
        return descriptionAbonnemment;
    }

    public void setDescriptionAbonnemment(String descriptionAbonnemment) {
        this.descriptionAbonnemment = descriptionAbonnemment;
    }

    public String getPrixAbonnemment() {
        return prixAbonnemment;
    }

    public void setPrixAbonnemment(String prixAbonnemment) {
        this.prixAbonnemment = prixAbonnemment;
    }

    @Override
    public String toString() {
        return "abonnement{" + "idAbonnement=" + idAbonnement + ", typeAbonnement=" + typeAbonnement + ", descriptionAbonnemment=" + descriptionAbonnemment + ", prixAbonnemment=" + prixAbonnemment + '}';
    }
    
    

}

