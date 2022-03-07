/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entites.Planning;
import entites.Programme;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServicePlanning;
import services.ServiceProgramme;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Ajout_PlanningController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private TextField nom_planning;
    @FXML
    private ComboBox <String>progcomb;
    @FXML
    private DatePicker date_planning;
    @FXML
    private ComboBox lieucomb;
    @FXML
    private TextArea description_planning;
    List <Programme>list_Programme;
    ObservableList <String>list_nom_programme;
    private int id_prog_select;
    private String lieu_select;
    ServicePlanning pr= new ServicePlanning();
    private Date date;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i;
        ServiceProgramme sp= new ServiceProgramme();
         list_Programme = sp.afficher();       
       List<String>  col = new ArrayList<String>();
        for(Programme si : list_Programme ){
        col.add(si.getNomProgramme());
        }
    list_nom_programme = FXCollections.observableArrayList(col);
    progcomb.setItems(list_nom_programme);
    ObservableList<String> list_lieu=FXCollections.observableArrayList ("salle 1", "salle 2", "salle 3");
         lieucomb.setItems(list_lieu);
         
          
        
       
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Gestion_planning.fxml"));
            Parent root = loader.load();
            nom_planning.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Ajout_PlanningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(MouseEvent event) {
        Planning p = new Planning();
    
    String tnom=nom_planning.getText();
    p.setNomPlanning(tnom);
    p.setIdProgramme(id_prog_select);
        
    p.setDatePlanning(date);
    p.setLieuPlanning(lieu_select);
    
    String tdes=description_planning.getText();
    p.setDescriptionPlanning(tdes);
    
    if (tnom.isEmpty() || tdes.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else{
    pr.ajouter(p);
    clean();
    }
    
    
    }

    @FXML
    private void getprog(ActionEvent event) {
        String prog_nom= progcomb.getSelectionModel().getSelectedItem();
        int prog_id=progcomb.getSelectionModel().getSelectedIndex();
         id_prog_select=list_Programme.get(prog_id).getIdProgamme();
    
    }

    @FXML
    private void getlieu(ActionEvent event) {
        lieu_select= lieucomb.getSelectionModel().getSelectedItem().toString();
    }
    private void clean() {
        nom_planning.setText(null);
        description_planning.setText(null);
        
    }

    @FXML
    private void getdate(ActionEvent event) {
         LocalDate mydate =date_planning.getValue();
        //Date d = mydate.todate;
        ZoneId defaultZoneId = ZoneId.systemDefault();
         date = java.sql.Date.valueOf(mydate);
        System.out.print(date);
    }
}
