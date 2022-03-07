 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Paragraph;
import entities.client;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import utils.myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author client
 */
public class clientcrud {
    Connection cnx2;
    public clientcrud()
    {
       cnx2 =  myconnection.getInstance().getCnx();
    }
    private static clientcrud instance;
    private String idUser;
    private String nom;
     private String prenom;
      private String dateNaissance;
       private String sexe;
        private String cin;
         private String numTel;
          private String email;
           private String password;
    
  public static clientcrud getInstance(){
        if(instance==null)
            instance=new clientcrud();
        return instance;
    }
    /**
     *
     * @param c
     * @throws java.sql.SQLException
     */
    public void ajouterclient2(client c) throws SQLException{
       
            String requete2 = "INSERT INTO user (nom,prenom,dateNaissance,sexe,cin,numTel,email,password)"
                    + "VALUES  (?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = cnx2.prepareStatement(requete2);
       pst.setString(1, c.getNom());
       pst.setString(2, c.getPrenom());
       pst.setString(3, c.getDateNaissance());
       pst.setString(4, c.getSexe() );
       pst.setString(5, c.getCin() );
       pst.setString(6, c.getNumTel() );
       pst.setString(7, c.getEmail() );
       pst.setString(8, c.getPassword() );
       
       pst.execute();
      
    }
    
    public ObservableList<client> afficherclients() throws SQLException{ 
         ObservableList<client> result = FXCollections.observableArrayList();
          String requete3 = "SELECT * FROM user";
      
           
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);  
            while(rs.next()){
                client c = new client();
                c.setIdUser(rs.getString("idUser"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setDateNaissance(rs.getString("dateNaissance"));
                c.setSexe(rs.getString("sexe"));
                c.setCin(rs.getString("cin"));
                c.setNumTel(rs.getString("numTel"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                result.add(c);
            }
        return result;
           
        
        }
         
    

 public void supprimerClient(String reference) throws SQLException
 {
     String requete4 = "DELETE FROM `user` WHERE dateNaissance= ?";
       
            
            
            PreparedStatement st = cnx2.prepareStatement(requete4);
            st.setString(1, reference);
            st.executeUpdate();
            
       
 }
 public void modifierClient(client c) throws SQLException
{
      
            
            String requete5 = "UPDATE `user` SET nom = ? ,prenom = ? ,dateNaissance = ? ,sexe = ? ,cin = ?,numTel = ?,email = ? ,password = ? WHERE idUser= ? ";
            
       PreparedStatement pst = cnx2.prepareStatement(requete5);
       pst.setString(1, c.getNom());
       pst.setString(2, c.getPrenom());
       pst.setString(3, c.getDateNaissance());
       pst.setString(4, c.getSexe() );
       pst.setString(5, c.getCin() );
       pst.setString(6, c.getNumTel() );
       pst.setString(7, c.getEmail() );
       pst.setString(8, c.getPassword() );
       pst.setString(9, c.getIdUser() );
       pst.executeUpdate();

}
  /*  public ObservableList<String>  rechercheclientid(int id){ 
         ObservableList<String>list = FXCollections.observableArrayList();
          String requete = "SELECT * FROM user WHERE idUser='"+id+"'";
        try {
           
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete);  
            while(rs.next()){
                client c = new client();
                c.setIdUser(rs.getString(1));
                c.setNom(rs.getString("nom"));
                 System.out.println("***");
                c.setPrenom(rs.getString("prenom"));
                c.setDateNaissance(rs.getString("dateNaissance"));
                c.setSexe(rs.getString("sexe"));
                c.setCin(rs.getString("cin"));
                c.setNumTel(rs.getString("numTel"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                list.add(c.toString());
            }
           
        } 
        catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
         return list;
    }
     public ObservableList<String>  rechercheclientnom(String nom){ 
         ObservableList<String>list = FXCollections.observableArrayList();
          String requete = "SELECT * FROM user WHERE nomUser='"+nom+"'";
        try {
           
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete);  
            while(rs.next()){
                client c = new client();
                c.setIdUser(rs.getString(1));
                c.setNom(rs.getString("nom"));
                 System.out.println("***");
                c.setPrenom(rs.getString("prenom"));
                c.setDateNaissance(rs.getString("dateNaissance"));
                c.setSexe(rs.getString("sexe"));
                c.setCin(rs.getString("cin"));
                c.setNumTel(rs.getString("numTel"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                list.add(c.toString());
            }
           
        } 
        catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
         return list;
    }
      public ObservableList<String>  triclientnom(){ 
         ObservableList<String>list = FXCollections.observableArrayList();
          String requete = "SELECT * FROM user ORDER BY nom";
        try {
           
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete);  
            while(rs.next()){
                client c = new client();
                c.setIdUser(rs.getString(1));
                c.setNom(rs.getString("nom"));
                 System.out.println("***");
                c.setPrenom(rs.getString("prenom"));
                c.setDateNaissance(rs.getString("dateNaissance"));
                c.setSexe(rs.getString("sexe"));
                c.setCin(rs.getString("cin"));
                c.setNumTel(rs.getString("numTel"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                list.add(c.toString());
            }
           
        } 
        catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
         return list;
    }
       public ObservableList<String>  triclientsexe(){ 
         ObservableList<String>list = FXCollections.observableArrayList();
          String requete = "SELECT * FROM user ORDER BY sexe";
        try {
           
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete);  
            while(rs.next()){
                client c = new client();
                c.setIdUser(rs.getString(1));
                c.setNom(rs.getString("nom"));
                 System.out.println("***");
                c.setPrenom(rs.getString("prenom"));
                c.setDateNaissance(rs.getString("dateNaissance"));
                c.setSexe(rs.getString("sexe"));
                c.setCin(rs.getString("cin"));
                c.setNumTel(rs.getString("numTel"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                list.add(c.toString());
            }
           
        } 
        catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
         return list;
    }
 */
        public String QR (String A) throws WriterException, IOException{
        
       
              
             try {
            String qrCodeData = "Client "+A+"";
            String filePath = "C:\\Users\\client\\Desktop\\connexionjdbc\\src\\QR\\"+A+".png";
            
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new EnumMap <  > (EncodeHintType.class);
            
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            return filePath;
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
           return "";
        }
               
       }
         public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

        
         public Paragraph listePdf() throws SQLException {
        Paragraph chaine=new Paragraph();
        ObservableList<String>list = FXCollections.observableArrayList();

        String req="SELECT * FROM `user`";

            Statement st=cnx2.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                client c = new client();
                c.setIdUser(rs.getString(1));
                c.setNom(rs.getString("nom"));
                 System.out.println("***");
                c.setPrenom(rs.getString("prenom"));
                c.setDateNaissance(rs.getString("dateNaissance"));
                c.setSexe(rs.getString("sexe"));
                c.setCin(rs.getString("cin"));
                c.setNumTel(rs.getString("numTel"));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                // p.setBirthdate(rst.getString("date_inscription"));

                chaine.add(c.toString()+"\n"+"\n");
               // chaine.add(""+addEmptyLine(chaine,1));//on retourne la liste
            }
        return chaine;
     
    }


}
