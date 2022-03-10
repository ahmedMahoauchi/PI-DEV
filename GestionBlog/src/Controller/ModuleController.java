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
    private Button rubriques;
    @FXML
    private Button blogs;
    @FXML
    private Button blogs1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rubriques(ActionEvent event) {
    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionRubriques.fxml"));
            Parent root = loader.load();
            
            rubriques.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void blogs(ActionEvent event) {
        
               try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionBlogs.fxml"));
            Parent root = loader.load();
            
            rubriques.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void blogs1(ActionEvent event) {
                  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AffichageBlogs.fxml"));
            Parent root = loader.load();
            
            rubriques.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
