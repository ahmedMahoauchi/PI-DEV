/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Planning;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ServicePlanning;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Gestion_planningController implements Initializable {

    @FXML
    private TableView<Planning> tableview;
    @FXML
    private TableColumn<Planning, String> nom_col;
    @FXML
    private TableColumn<Planning, Integer> programme_col;
    @FXML
    private TableColumn<Planning, Date> date_col;
    @FXML
    private TableColumn<Planning, String> lieu_col;
    @FXML
    private TableColumn<Planning, String> description_col;
    @FXML
    private TableColumn<Planning, String> editCol;
    @FXML
    private FontAwesomeIconView ajout;
    @FXML
    private FontAwesomeIconView imprimer;
    @FXML
    private TextField TFRecherche;
    ObservableList list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }    
    private void loadData(){
        ServicePlanning sp= new ServicePlanning();
         List Planning = sp.afficher();
        list = FXCollections.observableArrayList(Planning);
        
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomPlanning"));
        programme_col.setCellValueFactory(new PropertyValueFactory<>("nomProgramme"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("datePlanning"));
        lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieuPlanning"));
        description_col.setCellValueFactory(new PropertyValueFactory<>("descriptionPlanning"));
        tableview.setItems(list);
    }

    @FXML
    private void Get_Add(MouseEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Ajout_Planning.fxml"));
            Parent root = loader.load();
            
            ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Gestion_planningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Print(MouseEvent event) {
    }
    
}
