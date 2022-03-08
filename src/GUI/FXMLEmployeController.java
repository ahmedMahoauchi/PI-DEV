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


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import service.ServiceEmploye;







/**
 * FXML Controller class
 *
 * @author Packard bell
 */
public class FXMLEmployeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField TFId;

    @FXML
    private TextField TFNomEmploye;

    @FXML
    private DatePicker TFDateEmploye;

    @FXML
    private TextField TFNumEmploye;
    @FXML
    static TextField TFSalaireEmploye;
    
    
   @FXML
    private Label label;

    @FXML
    private TableView<Employe> tableview;
    
    @FXML
    private TableColumn<Employe, Integer> col_IdEmploye;

    @FXML
    private TableColumn<Employe, String> col_NomEmploye;

    @FXML
    private TableColumn<Employe, String> col_DateEmploye;

    @FXML
    private TableColumn<Employe, Integer> col_NumEmploye;
    @FXML
    private TableColumn<Employe, Integer> col_SalaireEmploye;

    
    ObservableList<Employe> listM;
    ObservableList<Employe> dataList;
    int index=-1;
    Connection cnx=null;
    ResultSet rs=null;
    PreparedStatement pst =null;
    
    
    @FXML
    private TextField TFRecherche;
    
    @FXML
    private Button btn_quitter;
    
    @FXML
    private PieChart pieChart;
    
    @FXML
    private ObservableList<PieChart.Data> data;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        searchEmploye();
        updateTable();
         

                
        
        
    }
    
    
     @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index =tableview.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   TFId.setText(col_IdEmploye.getCellData(index).toString());
   TFNomEmploye.setText(col_NomEmploye.getCellData(index).toString());
   TFNumEmploye.setText(col_NumEmploye.getCellData(index).toString());
   TFSalaireEmploye.setText(col_SalaireEmploye.getCellData(index).toString());
   
   
 

    }
    
    public void updateTable(){
        col_IdEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("IdEmploye"));
        col_NomEmploye.setCellValueFactory(new PropertyValueFactory<Employe,String>("NomEmploye"));
        col_DateEmploye.setCellValueFactory(new PropertyValueFactory<Employe,String>("DateEmploye"));
      
        col_NumEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("NumEmploye"));
        col_SalaireEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("SalaireEmploye"));
          
         try {
             listM=ServiceEmploye.getInstance().getAll();
             tableview.setItems(listM);
         } catch (SQLException ex) {
             Logger.getLogger(FXMLEmployeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
    
    //sql






    
    
    @FXML
    void addEmploye(ActionEvent event) throws SQLException {
        
            ServiceEmploye sc =new ServiceEmploye();
            Employe C =new Employe();
            C.setNomEmploye(TFNomEmploye.getText());
            C.setDateEmploye(String.valueOf(TFDateEmploye.getValue().toString()));
            C.setNumEmploye(Integer.parseInt(TFNumEmploye.getText()));
            C.setSalaireEmploye(Integer.parseInt(TFSalaireEmploye.getText()));
            
            
           String tnom=TFNomEmploye.getText();
           String tnum=TFNumEmploye.getText();
           String tsalaire=TFSalaireEmploye.getText();
            
            if (tnom.isEmpty()){
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
            }
            
           
          
            else if(!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", TFNumEmploye.getText()))
                 {

                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("User can not be created !!! ");
                   alert.setContentText("CIN and Phone must be formed with numbers only ( 8 numbers to be specific) !!! ");
                   alert.show(); 
               }
            else{
               
                    sc.add(C);
               
                   updateTable();
                  searchEmploye();

               
            
            //JOptionPane.showMessageDialog(null, "employé a ete ajouter avec succes");
            
            TFNomEmploye.setText("");
            //TFDateEmploye.setValue()==NULL;
            TFNumEmploye.setText(" ");
            TFSalaireEmploye.setText(" ");
            }
            
                
              
                
            
            
            
         
       
    }

    @FXML
    private void Edit(ActionEvent event) {
        ServiceEmploye SC =new ServiceEmploye();
        Employe C =new Employe();
            C.setNomEmploye(TFNomEmploye.getText());
            C.setDateEmploye(String.valueOf(TFDateEmploye.getValue().toString()));
            C.setNumEmploye(Integer.parseInt(TFNumEmploye.getText()));  
            C.setSalaireEmploye(Integer.parseInt(TFSalaireEmploye.getText())); 
            C.setIdEmploye(Integer.parseInt(TFId.getText()));
                
        try{
          
          SC.updateEmploye(C);
          updateTable();
         searchEmploye();
        JOptionPane.showMessageDialog(null, "L'employé à été modifier avec succes");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
        TFNomEmploye.setText("");
        //TFDateEmploye.setText("");
        TFNumEmploye.setText(" ");
        TFSalaireEmploye.setText(" ");
        
    }
    
    
    public void searchEmploye(){
        
        /*col_IdEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("IdEmploye"));
        col_NomEmploye.setCellValueFactory(new PropertyValueFactory<Employe,String>("NomEmploye"));
        col_DateEmploye.setCellValueFactory(new PropertyValueFactory<Employe,String>("DateEmploye"));
        col_NumEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("NumEmploye"));
        col_SalaireEmploye.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("SalaireEmploye"));
          */
        updateTable();
         try{
          dataList=ServiceEmploye.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Employe> filtredData = new FilteredList<>(dataList, b -> true);
          TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(person.getNomEmploye().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 
                 else if(String.valueOf(person.getNumEmploye()).indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }    
                  else if(String.valueOf(person.getSalaireEmploye()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Employe> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
    }
    
    
    
    @FXML
    private void Delete(ActionEvent event) {

        try{
        ServiceEmploye SC =new ServiceEmploye();
        Employe C =new Employe();
        
          SC.delete(TFId.getText());
            JOptionPane.showMessageDialog(null,"l'employe  a été supprimer avec succes");
            updateTable();
             searchEmploye();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
      
        }
        TFNomEmploye.setText("");
       // TFDateEmploye.setText(" ");
        TFNumEmploye.setText(" ");
         TFSalaireEmploye.setText(" ");
        
        
    }

    @FXML
    private void Quitter(ActionEvent event) throws Exception {
        try{
            btn_quitter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLMenu.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
               
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
    
     @FXML
    private void stats(ActionEvent event) throws Exception {
        try{
            btn_quitter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXMLStats.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
               
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
    
    
    public static boolean textNumeric(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNumeric = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[0-9]+")) {
            isNumeric = false;
            validationString = validationText;
        inputLabel.setText("hot arkam kahaw");
        }
        inputLabel.setText("only num");
        return isNumeric;

    }
    
    
   
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    
    @FXML
    private void vider(ActionEvent event) {
        TFNomEmploye.setText(" ");
       
        TFNumEmploye.setText(" ");
        TFSalaireEmploye.setText(" ");
        updateTable();
        
    }
    
}
