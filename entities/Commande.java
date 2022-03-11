package entities;

public class Commande {

    private int idCommande;
    private int idUser;
    private float prixCommande;
    private int isPayed;
    private String createdDTM;


    public Commande() {
    }


    public Commande(
            int idUser, double prixCommande, int isPayed, String createdDTM) {
        this.idUser = idUser;
        this.prixCommande = (float) prixCommande;
        this.isPayed = isPayed;
        this.createdDTM = createdDTM;
    }



    public Commande(int idCommande, int idUser, float prixCommande, int isPayed) {
        this.idCommande = idCommande;
        this.idUser = idUser;
        this.prixCommande = prixCommande;
        this.isPayed = isPayed;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public int getIdUser() {
        return idUser;
    }

    public float getPrixCommande() {
        return prixCommande;
    }

    public int getIsPayed() {
        return isPayed;
    }

    public String getCreatedDTM() {
        return createdDTM;
    }


    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setPrixCommande(float prixCommande) {
        this.prixCommande = prixCommande;
    }

    public void setIsPayed(int isPayed) {
        this.isPayed = isPayed;
    }

    public void setCreatedDTM(String createdDTM) {
        this.createdDTM = createdDTM;
    }


}
