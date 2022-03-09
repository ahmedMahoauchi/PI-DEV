/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Rubrique;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.RubriqueCRUD;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author oussama
 */
public class GestionRubriquesController implements Initializable {

    @FXML
    private TableColumn<Rubrique, String> id;
    @FXML
    private TableColumn<Rubrique, String> titre;
    @FXML
    private TableView<Rubrique> tableview;
    @FXML
    private FontAwesomeIconView ajout;
    @FXML
    private TableColumn<Rubrique, String> editCol;
    RubriqueCRUD pr = new RubriqueCRUD();
    @FXML
    private TextField TFRecherche;
    ObservableList<Rubrique> dataList;
    ObservableList list;
    /* Connection cnx2;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public GestionRubriques() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
*/
//ObservableList<Rubrique>  rubriques = FXCollections.observableArrayList();
    @FXML
    private FontAwesomeIconView retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
               loadData();
               
    }
            private void loadData()
    {
        // TODO
        RubriqueCRUD sp= new RubriqueCRUD();
         List rubriques = sp.afficher();
        ObservableList list = FXCollections.observableArrayList(rubriques);
        tableview.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    
            //add cell of button edit 
         Callback<TableColumn<Rubrique, String>, TableCell<Rubrique, String>> cellFoctory = (TableColumn<Rubrique, String> param) -> {
            // make cell containing buttons
            final TableCell<Rubrique, String> cell = new TableCell<Rubrique, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                        if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    // yes option

                          try{
                            Rubrique p = new Rubrique();
                            p=tableview.getSelectionModel().getSelectedItem();
                            pr.supprimer(p.getId());
                            JOptionPane.showMessageDialog(null,"la rubrique a été supprimeé avec succés");
                            loadData();
          
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null, e);}
                        }
                         else {
    // no option
                              }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Rubrique p = new Rubrique();
                            p=tableview.getSelectionModel().getSelectedItem();
                          
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Modifier_Rubriques.fxml"));
                            try {
                                 loader.load();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(GestionRubriquesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            Modifier_RubriquesController Modifier_RubriquesController = loader.getController();
                            Modifier_RubriquesController.setUpdate(true);
                            
                            Modifier_RubriquesController.setTextField(p.getId(),p.getTitre());
                            Parent root = loader.getRoot();
                            editIcon.getScene().setRoot(root);
                            
                            
                            
                            
                            /*Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();*/
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
       editCol.setCellFactory(cellFoctory);        
                 try{
         tableview.setItems(list);
         //recherche avec filtre
         FilteredList<Rubrique> filtredData = new FilteredList<>(list, b -> true);
         TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(p-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(p.getTitre().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }                
                
                 
                 
                     else
                     return false;
                 });
             });
         SortedList<Rubrique> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);
         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }
    }
     


 /* private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
*/
      
              @FXML
    private void retour(MouseEvent event) {
               try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Module.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionRubriquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            @FXML
    private void Get_Add(MouseEvent event) {
   
    try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ajout_Rubriques.fxml"));
            Parent root = loader.load();
             
            ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionRubriquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }


}
