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
public class reclammation {
    private String idReclamation;
    private String idUser;
    private String themeReclamation;
    private String description;

    public reclammation(String idReclamation, String idUser, String themeReclamation, String description) {
        this.idReclamation = idReclamation;
        this.idUser = idUser;
        this.themeReclamation = themeReclamation;
        this.description = description;
    }

    public reclammation(String idUser, String themeReclamation, String description) {
        this.idUser = idUser;
        this.themeReclamation = themeReclamation;
        this.description = description;
    }

    public reclammation() {
        
    }

    public String getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(String idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getThemeReclamation() {
        return themeReclamation;
    }

    public void setThemeReclamation(String themeReclamation) {
        this.themeReclamation = themeReclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "reclammation{" + "idReclamation=" + idReclamation + ", idUser=" + idUser + ", themeReclamation=" + themeReclamation + ", description=" + description + '}';
    }
   
    
}
