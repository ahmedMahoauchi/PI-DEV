/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class Programme {
    private int idProgamme;
    private int idUser;
    private int niveauProgramme;
    private int genreProgramme;
    private String nomProgramme;
    private String descriptionProgramme;
    private String typeProgramme;
    private String imageProgramme;

    public Programme(int idProgamme, int idUser, int niveauProgramme, int genreProgramme, String nomProgramme, String descriptionProgramme, String typeProgramme, String imageProgramme) {
        this.idProgamme = idProgamme;
        this.idUser = idUser;
        this.niveauProgramme = niveauProgramme;
        this.genreProgramme = genreProgramme;
        this.nomProgramme = nomProgramme;
        this.descriptionProgramme = descriptionProgramme;
        this.typeProgramme = typeProgramme;
        this.imageProgramme = imageProgramme;
    }

    public Programme() {
    }

   
    public Programme( int idUser, int niveauProgramme, int genreProgramme, String nomProgramme, String descriptionProgramme, String typeProgramme, String imageProgramme) {
        
        this.idUser = idUser;
        this.niveauProgramme = niveauProgramme;
        this.genreProgramme = genreProgramme;
        this.nomProgramme = nomProgramme;
        this.descriptionProgramme = descriptionProgramme;
        this.typeProgramme = typeProgramme;
        this.imageProgramme = imageProgramme;
    }

    public int getIdProgamme() {
        return idProgamme;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getNiveauProgramme() {
        return niveauProgramme;
    }

    public int getGenreProgramme() {
        return genreProgramme;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public String getDescriptionProgramme() {
        return descriptionProgramme;
    }

    public String getTypeProgramme() {
        return typeProgramme;
    }

    public String getImageProgramme() {
        return imageProgramme;
    }

    public void setIdProgamme(int idProgamme) {
        this.idProgamme = idProgamme;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNiveauProgramme(int niveauProgramme) {
        this.niveauProgramme = niveauProgramme;
    }

    public void setGenreProgramme(int genreProgramme) {
        this.genreProgramme = genreProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public void setDescriptionProgramme(String descriptionProgramme) {
        this.descriptionProgramme = descriptionProgramme;
    }

    public void setTypeProgramme(String typeProgramme) {
        this.typeProgramme = typeProgramme;
    }

    public void setImageProgramme(String imageProgramme) {
        this.imageProgramme = imageProgramme;
    }

    @Override
    public String toString() {
        return "programme{" + "idProgamme=" + idProgamme + ", idUser=" + idUser + ", niveauProgramme=" + niveauProgramme + ", genreProgramme=" + genreProgramme + ", nomProgramme=" + nomProgramme + ", descriptionProgramme=" + descriptionProgramme + ", typeProgramme=" + typeProgramme + ", imageProgramme=" + imageProgramme + '}';
    }
    
}
