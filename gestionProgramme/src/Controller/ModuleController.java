/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModuleController implements Initializable {

    @FXML
    private Button programmes;
    @FXML
    private Button plannings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void programme(ActionEvent event) {
    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Gestion_programme.fxml"));
            Parent root = loader.load();
            
            programmes.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void plannings(ActionEvent event) {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Gestion_planning.fxml"));
            Parent root = loader.load();
            
            programmes.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
