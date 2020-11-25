package model.employees;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private SimpleStringProperty emri = new SimpleStringProperty();
    private SimpleStringProperty mbiemri = new SimpleStringProperty();;

    public Admin() {
    }

    public Admin(String emri, String mbiemri) {
        this.emri.set(emri);
        this.mbiemri.set(mbiemri);
    }

    public String getEmri() {
        return emri.get();
    }

    public String getMbiemri() {
        return mbiemri.get();
    }

    public void setEmri(String emri) {
        this.emri.set(emri);
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri.set(mbiemri);
    }

    @Override
    public String toString() {
        return  emri.get() + " - " + mbiemri.get();
    }
}
