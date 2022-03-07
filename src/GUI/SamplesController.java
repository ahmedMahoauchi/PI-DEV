/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author client
 */
public class SamplesController implements Initializable {
   @FXML
    private Button btn_client;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    void interfaceclient(ActionEvent event) {
  try{
            btn_client.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLMenu.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce client");
                mainStage.show();
                JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface client");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("test");
        }
    }
}
