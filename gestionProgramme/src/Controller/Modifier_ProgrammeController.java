/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entites.Programme;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceProgramme;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Modifier_ProgrammeController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private TextField nom;
    @FXML
    private TextField genre;
    @FXML
    private TextField type;
    @FXML
    private TextField niveau;
    @FXML
    private TextField image;
    @FXML
    private TextArea description;
    @FXML
    private Button retour;
    private boolean update;
    int id_programme;
ServiceProgramme pr = new ServiceProgramme();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void modifier(ActionEvent event) {
     Programme p = new Programme();
    p.setIdProgamme(id_programme);
     p.setIdUser(1);
    p.setNomProgramme(nom.getText());
    p.setGenreProgramme(Integer.parseInt(genre.getText()));
    p.setNiveauProgramme(Integer.parseInt(niveau.getText()));
    p.setTypeProgramme(type.getText());
    p.setImageProgramme(image.getText());
    p.setDescriptionProgramme(description.getText());
    try{
    pr.modifier(p);
    JOptionPane.showMessageDialog(null,"le programme a été modifier avec succes");
    }catch(Exception e)
    {JOptionPane.showMessageDialog(null, e);}
                                    
    
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Gestion_programme.fxml"));
            Parent root = loader.load();
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Ajout_ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Gestion_programme.fxml"));
            Parent root = loader.load();
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Modifier_ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clean() {
        nom.setText(null);
        type.setText(null);
        image.setText(null);
        description.setText(null);
        niveau.setText(null);
        genre.setText(null);}
    
     void setTextField(int id,String name,String t, String i,String d,int n,int g) {

        id_programme=id;
        nom.setText(name);
        type.setText(t);
        image.setText(i);
        description.setText(d);
        niveau.setText(Integer.toString(n));
        genre.setText(Integer.toString(g));
        
        
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
  public void closeLogin() {
        Stage stage1 = (Stage) modifier.getScene().getWindow();
     stage1.close();
}
}