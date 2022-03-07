/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entites.Programme;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import services.ServiceProgramme;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Ajout_ProgrammeController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextArea description;
ServiceProgramme pr = new ServiceProgramme();
    @FXML
    private Button retour;
    @FXML
    private Button attach;
    String Filename;
    @FXML
    private ImageView image_view;
    @FXML
    private RadioButton h_button;
    @FXML
    private ToggleGroup g;
    @FXML
    private RadioButton f_button;
    private int ge;
    private String s;
    private String nivcomb;
    private int niv;
    @FXML
    private ComboBox  typecomb;
    @FXML
    private ComboBox niveaucomb;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=FXCollections.observableArrayList ("Full Body", "TRX", "Hiit"); 
        typecomb.setItems (list) ;
         ObservableList<String> listniveau=FXCollections.observableArrayList ("débutant", "intermédiaire", "avancé"); 
        niveaucomb.setItems (listniveau) ;


        // TODO
    }    
    @FXML
    private void getgenre(ActionEvent event) {
    if(h_button.isSelected()){
    ge=1;
    }
    else if (f_button.isSelected()){
    ge=0;
    }
    System.out.print(ge);
        }

    @FXML
    private void save(MouseEvent event) {
    
    
    Programme p = new Programme();
    p.setIdUser(1);
    String tnom=nom.getText();
    p.setNomProgramme(tnom);
    p.setGenreProgramme(ge);
    p.setNiveauProgramme(niv);
    p.setTypeProgramme(s);
    p.setImageProgramme( Filename);
    String tdes=description.getText();
    p.setDescriptionProgramme(tdes);
    
    if (tnom.isEmpty() || tdes.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        }else{
    pr.ajouter(p);
    clean();
    }}

    @FXML
    private void retour(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Gestion_programme.fxml"));
            Parent root = loader.load();
            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Ajout_ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clean() {
        nom.setText(null);
        
        description.setText(null);
        image_view.setImage(null);
        g.selectToggle(null);
    }

    @FXML
    private void attach(ActionEvent event) throws IOException {
    JFileChooser chooser=new JFileChooser();
    
    chooser.showOpenDialog(null);
    File F=chooser.getSelectedFile();
     Filename=F.getAbsolutePath();
    //image.setText(Filename);
    Image getAbsolutePeth=null;
    BufferedImage bf;
    bf = ImageIO.read(F);
    //ImageIcon icon = new ImageIcon (Filename);
 Image image = SwingFXUtils.toFXImage(bf, null);
 image_view.setImage(image);

    }

    @FXML
    private void gettype(ActionEvent event) {
                s= typecomb.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    private void getniveau(ActionEvent event) {
    nivcomb= niveaucomb.getSelectionModel().getSelectedItem().toString();
    if(nivcomb=="débutant"){
    niv=1;
    }
    else if (nivcomb=="intermédiaire"){
    niv=2;
    }
    else if (nivcomb=="avancé"){
    niv=3;
    }
    }

    
}
