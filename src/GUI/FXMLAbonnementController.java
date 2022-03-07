/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import com.google.zxing.WriterException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import entities.client;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.abonnement;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import service.abonnementcrud;
import service.clientcrud;

import utils.myconnection;


/**
 * FXML Controller class
 *
 * @author Packard bell
 */
public class FXMLAbonnementController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField TFId;
       @FXML
    private TextField TFdes;

    @FXML
    private TextField TFprix;

    @FXML
    private TextField TFtype;

   

    @FXML
    private TableColumn<abonnement, String> col_Id;

    @FXML
    private TableColumn<abonnement, String> col_Type;

    @FXML
    private TableColumn<abonnement, String> col_desc;

    @FXML
    private TableColumn<abonnement, String> col_prix;

    @FXML
    private TableView<abonnement> tableview;

    
ObservableList<abonnement> listM;
ObservableList<abonnement> dataList;
int index=-1;
Connection cnx=null;
ResultSet rs=null;
PreparedStatement pst =null;
    @FXML
    private TextField TFRecherche;
    @FXML
    private Button btn_quitter;
    @FXML
    
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index =tableview.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   TFId.setText(col_Id.getCellData(index).toString());
   TFdes.setText(col_desc.getCellData(index).toString());
   TFtype.setText(col_Type.getCellData(index).toString());
   TFprix.setText(col_prix.getCellData(index).toString());
   
 

}
    public void updateTable(){
        col_Id.setCellValueFactory(new PropertyValueFactory<abonnement,String>("idAbonnement"));
        col_Type.setCellValueFactory(new PropertyValueFactory<abonnement,String>("typeAbonnement"));
        col_desc.setCellValueFactory(new PropertyValueFactory<abonnement,String>("descriptionAbonnemment"));
        col_prix.setCellValueFactory(new PropertyValueFactory<abonnement,String>("prixAbonnemment"));
       

          
         try {
             listM=abonnementcrud.getInstance().afficherabonnements();
             tableview.setItems(listM);
         } catch (SQLException ex) {
             Logger.getLogger(FXMLClientController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        searchProduit();
        updateTable();
        
    }  
    @FXML
    void addCategorie(ActionEvent event) throws IOException, WriterException {
         //String nom = TFnom.getText();
        abonnementcrud sc =new abonnementcrud();
        abonnement C =new abonnement();
       C.setDescriptionAbonnemment(TFdes.getText());
       C.setPrixAbonnemment(TFprix.getText());
       C.setTypeAbonnement(TFtype.getText());
        try{
            sc.ajouterabonnement(C);
            updateTable();
             searchProduit();
            JOptionPane.showMessageDialog(null, "reclammation  ajouté avec succes");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        TFtype.setText(" ");
        TFdes.setText("");
       TFprix.setText(" ");
        
       
        
    }

    @FXML
    private void Edit(ActionEvent event) {
        Alert dialogC = new Alert(AlertType.CONFIRMATION);
dialogC.setTitle("A confirmation dialog-box");
dialogC.setHeaderText(null);
dialogC.setContentText("voulez vous modifier la reclammation "+ TFId.getText()+"??");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
abonnementcrud sc =new abonnementcrud();
        abonnement C =new abonnement();
       C.setDescriptionAbonnemment(TFdes.getText());
       C.setPrixAbonnemment(TFprix.getText());
       C.setTypeAbonnement(TFtype.getText());
       C.setIdAbonnement(TFId.getText());
       
                
        try{
          
          sc.modifierAbonnement(C);
          updateTable();
         searchProduit();
        JOptionPane.showMessageDialog(null, "la reclammation à été modifier avec succes");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        
        }
          TFdes.setText("");
       TFprix.setText(" ");
        TFtype.setText(" ");
}
else {
JOptionPane.showMessageDialog(null, "modification annulée");
}
    }
    public void searchProduit(){
        
         col_Id.setCellValueFactory(new PropertyValueFactory<abonnement,String>("idAbonnement"));
        col_Type.setCellValueFactory(new PropertyValueFactory<abonnement,String>("typeAbonnement"));
        col_desc.setCellValueFactory(new PropertyValueFactory<abonnement,String>("descriptionAbonnemment"));
        col_prix.setCellValueFactory(new PropertyValueFactory<abonnement,String>("prixAbonnemment"));
       
          
         try{
          dataList=abonnementcrud.getInstance().afficherabonnements();
          tableview.setItems(dataList);
          FilteredList<abonnement> filtredData = new FilteredList<>(dataList, b -> true);
          TFRecherche.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(person.getIdAbonnement().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }else if(person.getTypeAbonnement().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 }
                 
                     else
                     return false;
                 });
             });
         SortedList<abonnement> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);

         }catch(Exception e){
             System.out.println(e.getMessage());
             
         }          
    }
    @FXML
    private void Delete(ActionEvent event) {
Alert dialogC = new Alert(AlertType.CONFIRMATION);
dialogC.setTitle("A confirmation dialog-box");
dialogC.setHeaderText(null);
dialogC.setContentText("voulez vous supprimer la reclamation "+ TFId.getText()+"??");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
try{
         abonnementcrud sc =new abonnementcrud();
        abonnement C =new abonnement();
        
         sc.AnnulerAbonnement(TFdes.getText());
            JOptionPane.showMessageDialog(null,"reclammation supprimée à étè avec succes");
            updateTable();
             searchProduit();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
      
        }
       TFdes.setText("");
       TFprix.setText(" ");
        TFtype.setText(" ");}
else {
JOptionPane.showMessageDialog(null, "suppression annulée");
}
        
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
    private void imprimmer(ActionEvent event) {
        cnx= myconnection.getInstance().getCnx();
        try{
            
            JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\client\\Desktop\\connexionjdbc\\src\\reports\\report3.jrxml");  
            
        String sql="SELECT * FROM abonnement ";
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
     public void pdf() {

        String FILE = "C:\\Users\\client\\Desktop\\connexionjdbc\\ListeClients.pdf";
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.NORMAL, BaseColor.RED);
        Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                Font.BOLD);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.BOLD);

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            //addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void addTitlePage(Document document)
            throws DocumentException, SQLException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Liste des Clients "+ new Date()));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        
        addEmptyLine(preface, 3);
        
        preface.add(new clientcrud().listePdf());

        addEmptyLine(preface, 8);

        preface.add(new Paragraph(
                "Ce document est une version préliminaire et n'est pas soumis à votre accord de licence ou à tout autre accord avec Appolon gym ;-)."));

        document.add(preface);
    }
      private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }

    }
}
