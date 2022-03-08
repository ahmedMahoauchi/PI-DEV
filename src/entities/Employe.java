/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Packard bell
 */
public class Employe {

    
    //les attributs
    public int IdEmploye;
    private String NomEmploye;
    private String DateEmploye;
    private int NumEmploye;
    private int SalaireEmploye;
    
    

    //constructeur
    public Employe(int IdEmploye, String NomEmploye, String DateEmploye, int NumEmploye,int SalaireEmploye) {
        this.IdEmploye = IdEmploye;
        this.NomEmploye = NomEmploye;
        this.DateEmploye = DateEmploye;
        this.NumEmploye = NumEmploye;
         this.SalaireEmploye = SalaireEmploye;
    }
//constructeur par default
    public Employe() {
    }

    

    

    public int getIdEmploye() {
        return IdEmploye;
    }

    public String getNomEmploye() {
        return NomEmploye;
    }

    public String getDateEmploye() {
        return DateEmploye;
    }

    public int getNumEmploye() {
        return NumEmploye;
    }

    public void setIdEmploye(int IdEmploye) {
        this.IdEmploye = IdEmploye;
    }

    public void setNomEmploye(String NomEmploye) {
        this.NomEmploye = NomEmploye;
    }

    public void setDateEmploye(String DateEmploye) {
        this.DateEmploye = DateEmploye;
    }

    public void setNumEmploye(int NumEmploye) {
        this.NumEmploye = NumEmploye;
    }
    public int getSalaireEmploye() {
        return SalaireEmploye;
    }

    public void setSalaireEmploye(int SalaireEmploye) {
        this.SalaireEmploye = SalaireEmploye;
    }

    @Override
    public String toString() {
        return "Employe{" + "IdEmploye=" + IdEmploye + ", NomEmploye=" + NomEmploye + ", DateEmploye=" + DateEmploye + ", NumEmploye=" + NumEmploye + ", SalaireEmploye=" + SalaireEmploye + '}';
    }
    
    
    
   

    
    
    
}
