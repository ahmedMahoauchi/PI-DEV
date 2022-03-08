/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utiles.Maconnexion;

/**
 * FXML Controller class
 *
 * @author tab3ouch
 */
public class FXMLLoginController implements Initializable {

   @FXML
    private AnchorPane partie_login;
    @FXML
    private TextField TFNom;
    @FXML
    private TextField TFPassword;
    @FXML
    private ComboBox type;
    @FXML
    private AnchorPane partie_signup;
    @FXML
    private ComboBox typeUp;
    @FXML
    private TextField TFNomUp;
    @FXML
    private TextField TFPasswordUp;
    @FXML
    private TextField TFEmailUp;
     
    
    Connection cnx=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    @FXML
    private Button btn_login;
    @FXML
    private TextField TFIdUsers;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
        typeUp.getItems().addAll("Directeur","Cashier");
        type.getItems().addAll("Directeur","Cashier");
        
        // TODO
    } 
    
    
    
     @FXML
    public void LoginPanelShow(){
    partie_login.setVisible(true);
    partie_signup.setVisible(false);
            
}
    @FXML
    public void SignuppaneShow(){
   partie_login.setVisible(false);
   partie_signup.setVisible(true);        
            
            
    
}
    
    
    private void Login(ActionEvent event) throws Exception{
         cnx= Maconnexion.getInstance().getCnx();
        String req="select * from employes where NomEmploye =? and NumEmploye=? and type=? ";
        try{
            pst =cnx.prepareStatement(req);
            
            pst.setString(1, TFNom.getText());
            pst.setString(2, TFPassword.getText());
            pst.setString(3, type.getValue().toString());
            rs=pst.executeQuery();
            if(rs.next()){
                if(type.getValue().toString()=="Directeur"){
                
            
                JOptionPane.showMessageDialog(null, "le nom et le password sont correcte");
                
                btn_login.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLMenu.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                 mainStage.setTitle("Interfce Menu");
                mainStage.setScene(scene);
               
                mainStage.show();
                }else if(type.getValue().toString()=="Cashier"){
                     
                     JOptionPane.showMessageDialog(null, "le nom et le password sont correcte");
                    btn_login.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLMenuVendeur.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                 mainStage.setTitle("Interfce Menu Cashier");
                      mainStage.show();
                       btn_login.getScene().getWindow().hide();
                }
        
            }else
                JOptionPane.showMessageDialog(null, "le nom et le password sont incorrecte");
                    
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
        
            
         
        
    }
    
    
    
    
}
