/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author WINDOWS 8.1
 */
public class TableData {
    private final StringProperty id;
    private final StringProperty tgl;
    private final StringProperty tabung;
    
    public TableData(String id, String tanggal, String nabung) {
        this.id = new SimpleStringProperty(id);
        this.tgl = new SimpleStringProperty(tanggal);
        this.tabung = new SimpleStringProperty(nabung);
    }
    
     public String getId() {
        return id.get();
    }
      public String getTgl() {
        return tgl.get();
    }
       public String getTabung() {
        return tabung.get();
    }
    
     public void setId(String value) {
        id.set(value);
    }
      public void setTgl(String value) {
        tgl.set(value);
    }
       public void setTabung(String value) {
        tabung.set(value);
    }
       
       public StringProperty idProperty() {
        return id;
    }
       public StringProperty tglProperty() {
        return tgl;
    }
       public StringProperty tabungProperty() {
        return tabung;
    }
}
