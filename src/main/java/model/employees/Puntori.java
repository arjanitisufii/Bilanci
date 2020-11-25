package model.employees;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Puntori extends Admin {
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleLongProperty tel = new SimpleLongProperty();
    private SimpleDoubleProperty bilanci = new SimpleDoubleProperty();
    private SimpleStringProperty password = new SimpleStringProperty();

    public Puntori() {
    }

    public Puntori(String emri, String mbiemri, String email, long tel, double bilanci, String password) {
        super(emri, mbiemri);
        this.email.set(email);
        this.tel.set(tel);
        this.bilanci.set(bilanci);
        this.password.set(password);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public long getTel() {
        return tel.get();
    }

    public void setTel(long tel) {
        this.tel.set(tel);
    }

    public double getBilanci() {
        return bilanci.get();
    }

    public void setBilanci(double bilanci) {
        this.bilanci.set(bilanci);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Puntori) {
            Puntori puntori = (Puntori) obj;
            return puntori.getTel() == (tel.get());
        }
        return false;
    }
}
