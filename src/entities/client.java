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
public class client {
    private String idUser;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String sexe;
    private String cin;
    private String numTel;
    private String email;
    private String password;
        
    
    public client(){
        
    }

    public client(String idUser, String nom, String prenom, String dateNaissance, String sexe, String cin, String numTel, String email, String password) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.cin = cin;
        this.numTel = numTel;
        this.email = email;
        this.password = password;
    }

    public client(String nom, String prenom, String dateNaissance, String sexe, String cin, String numTel, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.cin = cin;
        this.numTel = numTel;
        this.email = email;
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "client{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe + ", cin=" + cin + ", numTel=" + numTel + ", email=" + email + ", password=" + password + '}';
    }

  
    
    
    
    
    
}
