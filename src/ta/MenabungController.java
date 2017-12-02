/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author WINDOWS 8.1
 */
public class MenabungController implements Initializable {
    
    @FXML
    private JFXDatePicker tanggalnabung;
    @FXML
    private JFXTextField nominalnabung;
    
    java.sql.Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    @FXML
    void tabung(ActionEvent event) {
        String tgl = tanggalnabung.getValue().toString();
        int nominal = Integer.parseInt(nominalnabung.getText());
        try {
            String sql = "INSERT INTO nabung (tanggalnabung,nominalnabung) VALUES ('"+tgl+"',"+nominal+")";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            ((Node)(event.getSource())).getScene().getWindow().hide();
                fxmlLoader.setLocation(getClass().getResource("home.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("X-Money");
                stage.show();
        }
         catch (IOException e) {
            System.out.println("Failed to create new Window." + e);
        }
    }

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
    private void kembali(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            ((Node)(event.getSource())).getScene().getWindow().hide();
                fxmlLoader.setLocation(getClass().getResource("home.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("X-Money");
                stage.show();
        }
         catch (IOException e) {
            System.out.println("Failed to create new Window." + e);
        }
    }
    
}
