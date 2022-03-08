/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;






import entities.Conge;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import utiles.Maconnexion;

/**
 * FXML Controller class
 *
 * @author Zakou
 */
public class FXMLStatsController implements Initializable {

    @FXML
    private PieChart piechart;
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
    
    
    
    
    
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cnx= Maconnexion.getInstance().getCnx();
        stat();
    }    
     private void stat()
    {
          
           
    
           
        try {
            String query = "SELECT COUNT(*),SalaireEmploye FROM Employes GROUP BY IdEmploye" ;
            
            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();
            
            
            while (rs.next()){
                data.add(new PieChart.Data(rs.getString("SalaireEmploye"),rs.getInt("COUNT(*)")));
            }
            
            
            piechart.setTitle("Statistiques des salaire des Employes");
            piechart.setLegendSide(Side.LEFT);
            piechart.setData(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLStatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}

