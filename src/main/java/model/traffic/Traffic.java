package model.traffic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Traffic {
    private SimpleStringProperty emri = new SimpleStringProperty();
    private SimpleStringProperty adresa = new SimpleStringProperty();
    private SimpleStringProperty tel = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleLongProperty nipt = new SimpleLongProperty();
    private SimpleLongProperty nrTVSH = new SimpleLongProperty();
    private SimpleStringProperty nrBiznesit = new SimpleStringProperty();
    private SimpleStringProperty nrLicenses = new SimpleStringProperty();

    public Traffic() {}

    public Traffic(String emri, String adresa, String tel, String email, long nipt,
                   long nrTVSH, String nrBiznesit, String nrLicenses) {
        this.emri.set(emri);
        this.adresa.set(adresa);
        this.tel.set(tel);
        this.email.set(email);
        this.nipt.set(nipt);
        this.nrTVSH.set(nrTVSH);
        this.nrBiznesit.set(nrBiznesit);
        this.nrLicenses.set(nrLicenses);
    }

    public String getEmri() {
        return emri.get();
    }

    public void setEmri(String emri) {
        this.emri.set(emri);
    }

    public String getAdresa() {
        return adresa.get();
    }


    public void setAdresa(String adresa) {
        this.adresa.set(adresa);
    }

    public String getTel() {
        return tel.get();
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public long getNipt() {
        return nipt.get();
    }

    public void setNipt(long nipt) {
        this.nipt.set(nipt);
    }

    public long getNrTVSH() {
        return nrTVSH.get();
    }

    public void setNrTVSH(long nrTVSH) {
        this.nrTVSH.set(nrTVSH);
    }

    public String getNrBiznesit() {
        return nrBiznesit.get();
    }

    public void setNrBiznesit(String nrBiznesit) {
        this.nrBiznesit.set(nrBiznesit);
    }

    public String getNrLicenses() {
        return nrLicenses.get();
    }

    public void setNrLicenses(String nrLicenses) {
        this.nrLicenses.set(nrLicenses);
    }

    @Override
    public String toString() {
        return this.getEmri();
    }
}
