package entities;

public class Categorie {
    private int idCategorie;
    private String nomCategorie;
    private String createdDTM;
    private int createdBy;

    public Categorie(String nomCategorie,String createdDTM, int createdBy) {
        this.nomCategorie = nomCategorie;
        this.createdDTM = createdDTM;
        this.createdBy = createdBy;
    }

    public Categorie() {
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }


    public String getCreatedDTM() {
        return createdDTM;
    }

    public int getCreatedBy() {
        return createdBy;
    }


    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }


    public void setCreatedDTM(String createdDTM) {
        this.createdDTM = createdDTM;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", nomCategorie='" + nomCategorie + '\'' +
                ", createdDTM='" + createdDTM + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
