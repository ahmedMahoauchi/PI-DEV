/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Programme;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.I;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import services.ServiceProgramme;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Gestion_programmeController implements Initializable {

    @FXML
    private TableColumn<Programme, String> nom;
    @FXML
    private TableColumn<Programme, String> description;
    @FXML
    private TableColumn<Programme, Integer> genre;
    @FXML
    private TableColumn<Programme, String> type;
    @FXML
    private TableColumn<Programme, String> image;
    @FXML
    private TableView<Programme> tableview;
    @FXML
    private TableColumn<Programme, Integer> niveau;
    @FXML
    private FontAwesomeIconView ajout;
    @FXML
    private TableColumn<Programme, String> editCol;
ServiceProgramme pr = new ServiceProgramme();
    @FXML
    private TextField TFRecherche;
    
    ObservableList list;
    @FXML
    private FontAwesomeIconView imprimer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       loadData();
        
    }  
    private void loadData()
    {
     ServiceProgramme sp= new ServiceProgramme();
         List Programme = sp.afficher();
        list = FXCollections.observableArrayList(Programme);
        
        //tableview.setItems(list);
        nom.setCellValueFactory(new PropertyValueFactory<>("nomProgramme"));
        description.setCellValueFactory(new PropertyValueFactory<>("descriptionProgramme"));
        niveau.setCellValueFactory(new PropertyValueFactory<>("niveauProgramme"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genreProgramme"));
        type.setCellValueFactory(new PropertyValueFactory<>("typeProgramme"));
        image.setCellValueFactory(new PropertyValueFactory<>("imageProgramme"));
    
    
            //add cell of button edit 
         Callback<TableColumn<Programme, String>, TableCell<Programme, String>> cellFoctory = (TableColumn<Programme, String> param) -> {
            // make cell containing buttons
            final TableCell<Programme, String> cell = new TableCell<Programme, String>() {
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

                          try{
                            Programme p = new Programme();
                            p=tableview.getSelectionModel().getSelectedItem();
                            pr.supprimer(p.getIdProgamme());
                            JOptionPane.showMessageDialog(null,"le programme  a été supprimer avec succes");
                            loadData();
          
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null, e);}

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            Programme p = new Programme();
                            p=tableview.getSelectionModel().getSelectedItem();
                          
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Modifier_Programme.fxml"));
                            try {
                                 loader.load();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(Gestion_programmeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            Modifier_ProgrammeController Modifier_ProgrammeController = loader.getController();
                            Modifier_ProgrammeController.setUpdate(true);
                            
                            Modifier_ProgrammeController.setTextField(p.getIdProgamme(),p.getNomProgramme(),p.getTypeProgramme(),p.getImageProgramme(),p.getDescriptionProgramme(),p.getNiveauProgramme(),p.getGenreProgramme());
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
         FilteredList<Programme> filtredData = new FilteredList<>(list, b -> true);
         TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(p-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(p.getNomProgramme().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(p.getDescriptionProgramme().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 else if(String.valueOf(p.getGenreProgramme()).indexOf(lowerCaseFilter)!=-1){
                     
                     return true;
                 }else if(String.valueOf(p.getNiveauProgramme()).indexOf(lowerCaseFilter)!=-1){
                 return true;
                 }else if(String.valueOf(p.getTypeProgramme()).indexOf(lowerCaseFilter)!=-1){
                 return true;
                 }else if(p.getImageProgramme().toLowerCase().indexOf(lowerCaseFilter)!=-1)
                 return true;
                 
                 
                     else
                     return false;
                 });
             });
         SortedList<Programme> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);
         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }  
    
    }

    @FXML
    private void Get_Add(MouseEvent event) {
   
    try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Ajout_Programme.fxml"));
            Parent root = loader.load();
            
            ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Gestion_programmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void Print(MouseEvent event) throws FileNotFoundException, DocumentException {
        
        String path="";
JFileChooser j=new JFileChooser ();
j.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY) ;

int x=j.showSaveDialog (j);
if (x==JFileChooser. APPROVE_OPTION)
    path=j.getSelectedFile ().getPath ();
Document doc=new Document ();
try{
   PdfWriter.getInstance (doc, new FileOutputStream (path+".pdf"));
   doc.open();
PdfPTable tbl=new PdfPTable(6);

//adding header
tbl.addCell ("nom");
tbl.addCell ("description");
tbl.addCell ("niveau");
tbl.addCell ("genre");
tbl.addCell ("type");
tbl.addCell ("image");

for (int i = 0; i < tableview.getItems().size(); i++) {
    

   String nomP=nom.getCellData(i);
   String descriptionP=description.getCellData(i);
   String niveauP=niveau.getCellData(i).toString();
   String genreP=genre.getCellData(i).toString();
   String typeP=type.getCellData(i);
   String imageP=image.getCellData(i);
   tbl.addCell (nomP);
   tbl.addCell (descriptionP);
   tbl.addCell (niveauP);
   tbl.addCell (genreP);
   tbl.addCell (typeP);
   tbl.addCell (imageP);
   
}
    doc.add(new Phrase(16, "\n\n\n"));
    doc.add(new Phrase(-16,"la liste de tes programmes"));	
    doc.add(new Phrase(16, "\n\n\n")); 
    doc.add(tbl);
     
}catch (FileNotFoundException ex) {
    Logger.getLogger (Gestion_programmeController.class.getName ()).log (Level.SEVERE, null, ex);
    }
doc.close();
    }
    
}
