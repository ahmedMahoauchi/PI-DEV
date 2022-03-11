/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * @author oussama
 */


public class Blog {
    private int id;
    private String titre;
    private String description;
    private String photo;
    private int votes;
    private Timestamp date;
    private int id_rubrique;
    private int id_user;
    
    
public Blog (){
}

    public Blog(int id) {
        this.id = id;
    }

    public Blog(int id, int id_rubrique) {
        this.id = id;
        this.id_rubrique = id_rubrique;
    }

    public Blog(int id, int id_rubrique, int id_user) {
        this.id = id;
        this.id_rubrique = id_rubrique;
        this.id_user = id_user;
    }

    public Blog(String titre, String description, String photo, int votes, int id_rubrique, int id_user) {
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.votes = votes;
        this.id_rubrique = id_rubrique;
        this.id_user = id_user;
    }

    public Blog(String titre, String description, String photo, int votes, int id_rubrique) {
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.votes = votes;
        this.id_rubrique = id_rubrique;
    }
    
     public Blog(String titre, String description, String photo, int id_rubrique) {
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.id_rubrique = id_rubrique;
    }
    

    public Blog(String titre, String description, String photo) {
        this.titre = titre;
        this.description = description;
        this.photo = photo;
    }
    

    
    
    public Blog(int id, String titre, String description, String photo, int votes, int id_rubrique, int id_user) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.votes = votes;
        this.id_rubrique = id_rubrique;
        this.id_user = id_user;
    }

        public Blog(int id, String titre, String description, String photo, int votes, Timestamp date, int id_rubrique, int id_user) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.votes = votes;
        this.date = date;
        this.id_rubrique = id_rubrique;
        this.id_user = id_user;
    }

    public Blog(int id, String titre, String description, String photo, int votes) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.votes = votes;
    }

    public Blog(int id, String titre, String description, String photo) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getId_rubrique() {
        return id_rubrique;
    }

    public void setId_rubrique(int id_rubrique) {
        this.id_rubrique = id_rubrique;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", photo=" + photo + ", votes=" + votes + ", date=" + date + ", id_rubrique=" + id_rubrique + ", id_user=" + id_user + '}';
    }  

    public void setId_rubrique(int i, String selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
}
