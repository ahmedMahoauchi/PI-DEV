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
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLConge.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce congés");
                mainStage.show();
                 System.out.println("test");
                
                JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface congé");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           
        }
        
    }

    @FXML
    private void interfaceEmploye(ActionEvent event)throws Exception {
         try{
             btn_Employe.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLEmploye.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce Employé");
                mainStage.show();
                JOptionPane.showMessageDialog(null, "Bienvenue dans votre interface des employés");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void Quitter(ActionEvent event) {
     Stage stage = (Stage) btn_quitter.getScene().getWindow();
    stage.close();
    }
    
    
     @FXML
    private void front(ActionEvent event) throws Exception {
        try{
            btn_quitter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLfrontEmploye.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
               
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }

    

 
    
}
