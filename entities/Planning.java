/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
import javafx.scene.control.DatePicker;
/**
 *
 * @author ASUS
 */
public class Planning {
    private int idPlanning;
    private int idProgramme;
    private String nomProgramme;
    private String nomPlanning;
    private String descriptionPlanning;
    private Date datePlanning;
    private String lieuPlanning;

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public Planning() {
    }

    public Planning(int idProgramme, String nomPlanning, String descriptionPlanning, Date datePlanning, String lieuPlanning) {
        this.idProgramme = idProgramme;
        this.nomPlanning = nomPlanning;
        this.descriptionPlanning = descriptionPlanning;
        this.datePlanning = datePlanning;
        this.lieuPlanning = lieuPlanning;
    }

    public Planning(int idPlanning, int idProgramme, String nomPlanning, String descriptionPlanning, Date datePlanning, String lieuPlanning) {
        this.idPlanning = idPlanning;
        this.idProgramme = idProgramme;
        this.nomPlanning = nomPlanning;
        this.descriptionPlanning = descriptionPlanning;
        this.datePlanning = datePlanning;
        this.lieuPlanning = lieuPlanning;
    }

    public int getIdPlanning() {
        return idPlanning;
    }

    public int getIdProgramme() {
        return idProgramme;
    }

    public String getNomPlanning() {
        return nomPlanning;
    }

    public String getDescriptionPlanning() {
        return descriptionPlanning;
    }

    public Date getDatePlanning() {
        return datePlanning;
    }

    public String getLieuPlanning() {
        return lieuPlanning;
    }

    public void setIdPlanning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    public void setIdProgramme(int idProgramme) {
        this.idProgramme = idProgramme;
    }

    public void setNomPlanning(String nomPlanning) {
        this.nomPlanning = nomPlanning;
    }

    public void setDescriptionPlanning(String descriptionPlanning) {
        this.descriptionPlanning = descriptionPlanning;
    }

    public void setDatePlanning(Date datePlanning) {
        this.datePlanning = datePlanning;
    }

    public void setLieuPlanning(String lieuPlanning) {
        this.lieuPlanning = lieuPlanning;
    }

    @Override
    public String toString() {
        return "Planning{" + "idPlanning=" + idPlanning + ", idProgramme=" + idProgramme + ", nomPlanning=" + nomPlanning + ", descriptionPlanning=" + descriptionPlanning + ", datePlanning=" + datePlanning + ", lieuPlanning=" + lieuPlanning + '}';
    }

    
    
    
}
