/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.Conge;




import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import service.ServiceConge;
import utiles.Maconnexion;

/**
 * FXML Controller class
 *
 * @author Packard bell
 */
public class FXMLCongeController implements Initializable {

    @FXML
    private TextField TFId;
    
    @FXML
    private DatePicker TFDebutConge;

    @FXML
    private TextField TFTypeConge;

    @FXML
    private DatePicker TFFinConge;

    @FXML
    private TableView<Conge> tableview;
    @FXML
    private TableColumn<Conge, Integer> col_id;

   

    @FXML
    private TableColumn<Conge, String> col_TypeConge;

    @FXML
    private TableColumn<Conge, ComboBox> col_IdEmploye;

    @FXML
    private TableColumn<Conge, String> col_DebutConge;

    @FXML
    private TableColumn<Conge, String> col_FinConge;
     @FXML
    private TextField TFFilter;

   ObservableList<Conge> listM;
ObservableList<Conge> dataList;
int index=-1;
    
    /* ObservableList<Conge> obl = FXCollections.observableArrayList();
    ObservableList list = FXCollections.observableArrayList();
    ObservableList<Conge> dataList = FXCollections.observableArrayList();*/
    
    
   
    Connection cnx=null;
    ResultSet rs=null;
    PreparedStatement pst =null;
    @FXML
    private Button btn_quitter;
    @FXML
    private ComboBox IdEmploye;
    @FXML
    private Button bnt_imprimer;
    @FXML
    private Button bnt_actualiser;
    @FXML
    private ComboBox<String> triBox;
   
 

    
    
    
    
          
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<String> ids= new ArrayList<>();
        // TODO
         try{
            
            cnx= Maconnexion.getInstance().getCnx();
            
            String sql="select IdEmploye from employes ";
            PreparedStatement st = cnx.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            
            
            
            
            int index=0;
            while(rs.next()){
               IdEmploye.getItems().add(index,rs.getInt("IdEmploye"));
                ids.add(index, String.valueOf(rs.getInt("IdEmploye")));
                index++;
            }
    
        
        }catch(Exception e){
            System.out.println(e);
            
        }
        ObservableList<String> data = FXCollections.observableArrayList("IdEmploye", "TypeConge");
        triBox.setItems(data);

           
          
        updateTable();
        searchConge();
       
       
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @FXML
    private void AddConge(ActionEvent event) throws SQLException {
       ServiceConge sp = new ServiceConge();
       Conge p = new Conge();
        
        
        p.setTypeConge(TFTypeConge.getText());
        p.setIdEmploye(Integer.parseInt(IdEmploye.getValue().toString()));
        p.setDebutConge(String.valueOf(TFDebutConge.getValue().toString()));
        p.setFinConge(String.valueOf(TFFinConge.getValue().toString()));
        
        
            
                
                if (TFDebutConge.getValue().toString().compareTo(TFFinConge.getValue().toString())==1)
                    {
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setTitle("invalid !!! ");
                         alert.setContentText(" Veuillez entrer un congé valide !!! ");
                         alert.show(); 
                         
                        
                    }
                else {
                
               sp.add(p);
               updateTable();
              searchConge();
              JOptionPane.showMessageDialog(null, "le conge à été ajouter  avec succes");
                }
          
           
            
          

        
            
    
       
        TFTypeConge.setText("");
        //TFDebutConge.setValue(" ");
       // TFFinConge.setText(" ");
        IdEmploye.setValue(" ");
        
        
    }
/////methode select Conges///
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index =tableview.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   TFId.setText(col_id.getCellData(index).toString());
  
   TFTypeConge.setText(col_TypeConge.getCellData(index).toString());
     IdEmploye.setValue(col_IdEmploye.getCellData(index));
   
  // TFDebutConge.setText(col_DebutConge.getCellData(index).toString());
  // TFFinConge.setText(col_FinConge.getCellData(index).toString());
   

    }
    
    
     
    
    
    
    public void updateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Conge,Integer>("IdConge"));
        
        col_TypeConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("TypeConge"));
        col_IdEmploye.setCellValueFactory(new PropertyValueFactory<Conge, ComboBox>("IdEmploye"));
        col_DebutConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("DebutConge"));
          col_FinConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("FinConge"));
         try {
             listM=ServiceConge.getInstance().getAll();
             tableview.setItems(listM);
         } catch (SQLException ex) {
             Logger.getLogger(FXMLEmployeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void Edit(ActionEvent event) {
        ServiceConge sp = new ServiceConge();
        Conge p= new Conge();
        
         
        p.setTypeConge(TFTypeConge.getText());
        p.setIdEmploye(Integer.parseInt(IdEmploye.getValue().toString()));
        p.setDebutConge(String.valueOf(TFDebutConge.getValue().toString()));
        p.setFinConge(String.valueOf(TFFinConge.getValue().toString()));
        p.setIdConge(Integer.parseInt(TFId.getText()));
       
        try{
        
         sp.update(p);
         updateTable();
        
        JOptionPane.showMessageDialog(null, "Le Conge à ete modifier avec succes");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        TFTypeConge.setText(" ");
       // TFDebutConge.setText(" ");
       //  TFFinConge.setText("");
        IdEmploye.setValue("");
    
    }
    
   /* public void trie(){
    Comparator<Conge> comparator = null;
        if(triBox.getValue()=="IdEmploye"){
         comparator = Comparator.comparingInt(Conge::getIdEmploye);
         
        }
        else  if(triBox.getValue()=="TypeEmploye"){
         comparator = Comparator.comparing(Conge::getTypeConge);
         
        }
       
        
        FXCollections.sort(obl, comparator);
           tableview.setItems(obl);
           updateTable();
    
    }
    
    
   */
           
           
           
    
     public void searchConge(){
        /*col_id.setCellValueFactory(new PropertyValueFactory<Conge,Integer>("IdConge"));
        
        col_TypeConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("TypeConge"));
        col_IdEmploye.setCellValueFactory(new PropertyValueFactory<Conge, ComboBox>("IdEmploye"));
        
         col_DebutConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("DebutConge"));
        col_FinConge.setCellValueFactory(new PropertyValueFactory<Conge,String>("FinConge"));
*/
        updateTable();
         try{
             dataList=ServiceConge.getInstance().getAll();
          tableview.setItems(dataList);
          FilteredList<Conge> filtredData = new FilteredList<>(dataList, b -> true);
          TFFilter.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                
                 if(person.getTypeConge().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(person.getIdConge()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Conge> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
          
          
     }
     
     
     
     
     
     
     
     @FXML
    private void imprimer(ActionEvent event) {
        cnx= Maconnexion.getInstance().getCnx();
        try{
            
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\tab3ouch\\OneDrive\\Desktop\\GestionRH\\src\\reports\\report1.jrxml");  
            
        String sql="SELECT IdConge, IdEmploye,DebutConge,FinConge,TypeConge FROM conge ";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasperDesign.setQuery(newQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cnx);
            JasperViewer.viewReport(jasperPrint,false);           
        } catch (JRException ex) {
             System.out.println(ex.getMessage());
        }
    
        
        
        
        
    }
    
    
    

    @FXML
    private void Delete(ActionEvent event) {
      
       ServiceConge sp= new ServiceConge();
       Conge p = new Conge();
     
        try{
           
           sp.deleteConge(Integer.parseInt(TFId.getText()));
           
           
            JOptionPane.showMessageDialog(null,"le congé a été supprimer avec succes");
            updateTable();
            searchConge();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       
        
    }
    
    
    @FXML
    private void vider(ActionEvent event) {
        TFTypeConge.setText(" ");
       // TFDebutConge.setText(" ");
      //  TFFinConge.setText("");
        IdEmploye.setValue(" ");
        updateTable();
        
    }

    @FXML
    private void Quitter(ActionEvent event) throws Exception{
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
    
    
    


   
       
    }
