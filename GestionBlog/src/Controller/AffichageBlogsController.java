/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Blog;
import entities.Rubrique;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
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
public class AffichageBlogsController implements Initializable {

    @FXML
    private FontAwesomeIconView ajout;
    @FXML
    private TableColumn<Blog, String> id;
    @FXML
    private TableColumn<Blog, String> titre;
    @FXML
    private TableColumn<Blog, String> description;
    @FXML
    private TableColumn<Blog, String> photo;
    @FXML
    private TableColumn<Blog, String> votes;
    @FXML
    private TableColumn<Blog, String> date;
    @FXML
    private TableColumn<Blog, String> id_rubrique;
    @FXML
    private TableColumn<Blog, String> id_user;
    @FXML
    private TableView<Blog> tableview;

    @FXML
        private TableColumn<Blog, Image> preview;
    @FXML
    private TableColumn<Blog, String> like;
    @FXML
    private ComboBox<String> rubrique;
    BlogCRUD pr = new BlogCRUD();
    @FXML
    private TextField TFRecherche;
    ObservableList<Blog> dataList;
    ObservableList list;
   @FXML
    private FontAwesomeIconView retour;
    
         Connection cnx2;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
 

  

      public AffichageBlogsController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rubrique.setPromptText("choisissez");
         fillCombo(); 
         loadData();
         
    }

        private void loadData()
    {
        
    BlogCRUD sp= new BlogCRUD();
         List blogs = sp.afficher();
        ObservableList list = FXCollections.observableArrayList(blogs);
      
Blog s = new Blog();
    preview.setCellFactory(new Callback<TableColumn<Blog, Image>, TableCell<Blog, Image>>() 
    {
     
        public TableCell<Blog, Image> call(TableColumn<Blog, Image> param) 
        {
             final ImageView imgView = new ImageView();
            imgView.setFitHeight(70);
            imgView.setFitWidth(70);

            TableCell<Blog, Image> cell = new TableCell<Blog, Image>() 
            {
                public void updateItem(Image image, boolean empty)
                {super.updateItem(image, empty);
                    if(image != null)
                    { 
                        try {
                            image = new Image( new FileInputStream(s.getPhoto()));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GestionBlogsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                        imgView.setImage(image);
                    }
                }
            };
            cell.setGraphic(imgView);
            return cell;
        }
    });

 
  tableview.setItems(list);

        //preview.setCellValueFactory(new PropertyValueFactory<>("photo"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        votes.setCellValueFactory(new PropertyValueFactory<>("votes"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id_rubrique.setCellValueFactory(new PropertyValueFactory<>("id_rubrique"));
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
    
     //add cell of button edit 
   
         
         /*
                           Callback<TableColumn<Blog, String>, TableCell<Blog, String>> cellFoctory1 = (TableColumn<Blog, String> param) -> {
            // make cell containing buttons
             TableCell<Blog, String> cell1 = new TableCell<Blog, String>() {
            
                public void updateItem1(String item, boolean empty) {
                
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                    FontAwesomeIconView likeIcon = new FontAwesomeIconView(FontAwesomeIcon.THUMBS_UP);
                    
                    likeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                              
                        );
                    likeIcon.setOnMouseClicked((MouseEvent event) -> {

                          try{
                            Blog b = new Blog();
                            b=tableview.getSelectionModel().getSelectedItem();
                            pr.supprimer(b.getId());
                            JOptionPane.showMessageDialog(null,"le blog a été supprimeé avec succés");
                            loadData();
          
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null, e);}

                        });
                    HBox managebtn1 = new HBox(likeIcon);
                        managebtn1.setStyle("-fx-alignment:center");
                     
                        HBox.setMargin(likeIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn1);

                        setText(null);

                    }
                }

            };

            return cell1;
        };
         like.setCellFactory(cellFoctory1); 
*/

        
                 try{
         tableview.setItems(list);
         //recherche avec filtre
         FilteredList<Blog> filtredData = new FilteredList<>(list, b -> true);
         TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(p-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(p.getTitre().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(p.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;                
                 }else if(p.getPhoto().toLowerCase().indexOf(lowerCaseFilter)!=-1)
                 return true;
                 
                 
                     else
                     return false;
                 });
             });
         SortedList<Blog> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);
         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }
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

    @FXML
    private void retour(MouseEvent event) {
               try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Module.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionBlogsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
        @FXML
        private void Get_Add(MouseEvent event) {
   
    try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Ajout_Blogs.fxml"));
            Parent root = loader.load();
            
            ajout.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionBlogsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void choisir(ActionEvent event) {
    
                        
                          ObservableList<Blog> list2 = FXCollections.observableArrayList();
                 try{
                     String selected =rubrique.getValue();
        String sql2= "SELECT id FROM rubrique WHERE titre='"+selected+"'";
         try {
         
           pst = cnx2.prepareStatement(sql2);
            rs= pst.executeQuery();
           
            while(rs.next()){
             
            int input;
            input = rs.getInt(1);           
            //pr.afficher2(input);       
            String sql3= "SELECT * FROM blog WHERE id_rubrique='"+input+"'";
        
         
       
        try {
            ste = cnx2.createStatement();
            rs = ste.executeQuery(sql3);
            while (rs.next()) {
                list2.add(new Blog(rs.getInt("id"), rs.getString("titre"), rs.getString("description"), rs.getString("photo"), rs.getInt("votes"), rs.getTimestamp("date"), rs.getInt("id_rubrique"), rs.getInt("id_user")));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(RubriqueCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
         tableview.setItems(list2);
         //recherche avec filtre
         FilteredList<Blog> filtredData = new FilteredList<>(list2, b -> true);
         TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(p-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(p.getTitre().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(p.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;                
                 }else if(p.getPhoto().toLowerCase().indexOf(lowerCaseFilter)!=-1)
                 return true;
                 
                 
                     else
                     return false;
                 });
             });
         SortedList<Blog> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);
         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }
        
           
      
            
            //List blogs1 = pr.afficher2(input);
        //ObservableList list2 = FXCollections.observableArrayList(blogs1);
               // tableview.setItems(list2);
        }
         } catch (Exception e){};}
        catch (Exception e){};

    }


    
}
