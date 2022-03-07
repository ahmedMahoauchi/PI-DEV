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
 * @author Packard bell
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Button btn_Conge;
      @FXML
    private Button btn_Conge1;
    @FXML
    private Button btn_Employe;
   
    @FXML
    private Button btn_quitter;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void interfaceConge(ActionEvent event) throws Exception  {
        try{
            btn_Conge.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLAbonnement.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce abonnements");
                mainStage.show();
                JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface reclammations");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("test");
        }
        
    }
 
    @FXML
    private void interfaceEmploye(ActionEvent event)throws Exception {
         try{
             btn_Employe.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLClient.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce client");
                mainStage.show();
                JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface des client");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
 
    /*private void interfacerecla(ActionEvent event)throws Exception {
         try{
             btn_reclas.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLreclammation.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce reclamation");
                mainStage.show();
                JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface des employés");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
*/
    @FXML
    private void Quitter(ActionEvent event) {
     Stage stage = (Stage) btn_quitter.getScene().getWindow();
    stage.close();
    }

    

 
    
}
