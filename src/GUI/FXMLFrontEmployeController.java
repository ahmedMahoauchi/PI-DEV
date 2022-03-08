/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Employe;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utiles.Maconnexion;

/**
 * FXML Controller class
 *
 * @author tab3ouch
 */


public class FXMLFrontEmployeController implements Initializable {

   
      
       @FXML
       Label label;
      
  
     

   
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         String salaire=FXMLEmployeController.TFSalaireEmploye.getText();
         displayName(salaire);
        
        
      
        
        
    }  
    public void displayName(String salaire)
              {
                  label.setText("salaire" + salaire);
              }
    
    
     
         
        
     
    
}
