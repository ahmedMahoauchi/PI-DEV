package entities;

public class Produit {
    private int indProduit;
    private int idCategorie;
    private String nomProduit;
    private String descriptionProduit;
    private double prixProduit;
    private int quantiteProduit;
    private String imageProduit;
    private String createdDTM;
    private int createdBy;

    //Constructors

    public Produit(int idCategorie, String nomProduit, String descriptionProduit, float prixProduit, int quantiteProduit, String imageProduit, String createdDTM, int createdBy) {
        this.idCategorie = idCategorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
        this.imageProduit = imageProduit;
        this.createdDTM = createdDTM;
        this.createdBy = createdBy;
    }

    public Produit(int indProduit, String nomProduit, String descriptionProduit, double prixProduit, int quantiteProduit) {
        this.indProduit = indProduit;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
    }

    public Produit() {

    }

    public Produit(int indProduit, int idCategorie, String nomProduit, String descriptionProduit, double prixProduit, int quantiteProduit, String imageProduit, String createdDTM, int createdBy) {
        this.indProduit = indProduit;
        this.idCategorie = idCategorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
        this.imageProduit = imageProduit;
        this.createdDTM = createdDTM;
        this.createdBy = createdBy;
    }

    // Getters
    public int getIndProduit() {
        return indProduit;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public String getCreatedDTM() {
        return createdDTM;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    //Setters


    public void setIndProduit(int indProduit) {
        this.indProduit = indProduit;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    public void setCreatedDTM(String createdDTM) {
        this.createdDTM = createdDTM;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    //ToString
    @Override
    public String toString() {
        return "Produit{" +
                "indProduit=" + indProduit +
                ", idCategorie=" + idCategorie +
                ", nomProduit='" + nomProduit + '\'' +
                ", descriptionProduit='" + descriptionProduit + '\'' +
                ", prixProduit=" + prixProduit +
                ", quantiteProduit=" + quantiteProduit +
                ", imageProduit='" + imageProduit + '\'' +
                ", createdDTM='" + createdDTM + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
