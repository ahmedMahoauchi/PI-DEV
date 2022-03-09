/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entities.Blog;

import entities.Rubrique;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import services.BlogCRUD;
import services.RubriqueCRUD;
import utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author oussama
 */
public class Ajout_BlogsController implements Initializable {

    BlogCRUD pr = new BlogCRUD();
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField txt_filename;
    @FXML
    private Button retour;


     Connection cnx2;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private ImageView image_view;
    public Ajout_BlogsController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    @FXML
    private ComboBox<String> rubrique;
    
    
    

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
        rubrique.setPromptText("choisissez");
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) throws SQLException {
    Blog p = new Blog();
      pst= cnx2.prepareStatement("SELECT COUNT(*) FROM blog WHERE titre=?");
pst.setString(1,titre.getText());
 rs = pst.executeQuery();
int n = 0;
if ( rs.next() ) {
    n = rs.getInt(1);
    
}
if((titre.getText().isEmpty())||(titre.getText().chars().allMatch(Character::isWhitespace))) {
    JOptionPane.showMessageDialog(null,"le titre est null, réessayez");
}
else if(rubrique.getSelectionModel().getSelectedItem()==null){
JOptionPane.showMessageDialog(null,"la rubrique est null, choisissez");
}
else {
if ( n == 0) {

       p.setTitre(titre.getText());
    p.setDescription(description.getText());
    p.setPhoto(txt_filename.getText());
    
    
   String selected =rubrique.getValue();
        String sql2= "SELECT id FROM rubrique WHERE titre='"+selected+"'";
         try {
         
           pst = cnx2.prepareStatement(sql2);
            rs= pst.executeQuery();
           
            while(rs.next()){
            int input;
            input = rs.getInt(1);
            p.setId_rubrique(input);
        }
         }
        catch (Exception e){}
      
         

    pr.ajouter2(p);
    clean();
   // do what ever you need, if the row exists  
}

else
{ 
JOptionPane.showMessageDialog(null,"le blog déja existe, réessayez");
    }

}
 
    }
    private void clean() {
        titre.setText(null);
        description.setText(null);
        txt_filename.setText(null);

    }
    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionBlogs.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Ajout_BlogsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void attach(MouseEvent event) throws IOException {
           JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        txt_filename.setText(filename);
    File F=chooser.getSelectedFile();
     String Filename=F.getAbsolutePath();
    //image.setText(Filename);
    javafx.scene.image.Image getAbsolutePeth=null;
    BufferedImage bf;
    bf = ImageIO.read(F);
    //ImageIcon icon = new ImageIcon (Filename);
 javafx.scene.image.Image image = SwingFXUtils.toFXImage(bf, null);
 image_view.setImage(image);
    }
    
    private void fillCombo() {
                String sql= "SELECT titre FROM rubrique";
                ObservableList<String> list = FXCollections.observableArrayList();
        try {
            pst = cnx2.prepareStatement(sql);
            rs= pst.executeQuery();
           
            while(rs.next()){

                list.add((rs.getString("titre")));

            }
    rubrique.setItems(list);
        }
        catch (Exception e){}
        
     
   
        
    }
}
