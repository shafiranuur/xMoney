/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta;

import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WINDOWS 8.1
 */
public class HomeController implements Initializable {
    
       
    @FXML
    private TextField saldo;
    
    @FXML
    private TableView<TableData> table;
    @FXML
    private TableColumn<TableData, String> idtabel;
    @FXML
    private TableColumn<TableData, String> tgltabel;
    @FXML
    private TableColumn<TableData, String> saldotabel;
    @FXML
    private JFXRadioButton usd;
    @FXML
    private JFXRadioButton krw;
    @FXML
    private JFXRadioButton sar;
    @FXML
    private TextField saldokonv;
    private ObservableList<TableData> data;
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    double jumlah = 0;
    double konversian;
    String tampilsaldokonv;
    @FXML
    private ToggleGroup konversi;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }    
    
    
    @FXML
    void update(ActionEvent event) throws SQLException {
         try {
            data = FXCollections.observableArrayList();
            rs = stat.executeQuery("SELECT * FROM nabung");
            while (rs.next()) {
                data.add(new TableData(rs.getString(1), rs.getString(2), rs.getString(3)));
                jumlah += Integer.parseInt(rs.getString(3));
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        idtabel.setCellValueFactory(new PropertyValueFactory<>("id"));
        tgltabel.setCellValueFactory(new PropertyValueFactory<>("tgl"));
        saldotabel.setCellValueFactory(new PropertyValueFactory<>("tabung"));
        
        
        table.setItems(null);
        table.setItems(data);
        
        saldo.setText(""+jumlah);
    }
    
    @FXML
    void konversi(ActionEvent event) {
        if(usd.isSelected()){
            konversian = jumlah * 0.000074 ;
        }
        else if(krw.isSelected()){
            konversian = jumlah * 0.080;
        }
        else if(sar.isSelected()){
            konversian = jumlah * 0.00028;
        }
        
        tampilsaldokonv = String.valueOf(konversian);
        saldokonv.setText(tampilsaldokonv);
           
    }
   
    
    @FXML
    private void menabung(ActionEvent event) {
        try {
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            // Load the new fxml
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("menabung.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            
            // Create new stage (window), then set the new Scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Menabung Yuk!");
            stage.show();
            
        } catch (IOException e) {
            System.out.println("Failed to create new Window." + e);
        }
    }
    
}
