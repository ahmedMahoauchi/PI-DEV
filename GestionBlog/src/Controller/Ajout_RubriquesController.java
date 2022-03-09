/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Rubrique;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.RubriqueCRUD;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Ajout_RubriquesController implements Initializable {

    @FXML
    private TextField titre;
    RubriqueCRUD pr = new RubriqueCRUD();
    @FXML
    private Button retour;
           Connection cnx2;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

  

      public Ajout_RubriquesController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) throws SQLException {
  pst= cnx2.prepareStatement("SELECT COUNT(*) FROM rubrique WHERE titre=?");
pst.setString(1,titre.getText());
 rs = pst.executeQuery();
int n = 0;
if ( rs.next() ) {
    n = rs.getInt(1);
    
}
if((titre.getText().isEmpty())||(titre.getText().chars().allMatch(Character::isWhitespace))) {
    JOptionPane.showMessageDialog(null,"la rubrique est null, réessayez");
}
else {
if ( n == 0) {
        Rubrique p = new Rubrique();

    p.setTitre(titre.getText());
    pr.ajouter(p);
    clean();
   // do what ever you need, if the row exists  
}

else
{ 
JOptionPane.showMessageDialog(null,"la rubrique déja existe, réessayez");
    }

}

    }

    @FXML
    private void retour(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionRubriques.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Ajout_RubriquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clean() {
        titre.setText(null);
        
        
        
    }
}
