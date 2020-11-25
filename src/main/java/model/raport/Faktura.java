package model.raport;

import javafx.beans.property.SimpleStringProperty;
import model.employees.Puntori;
import model.items.Malli;
import model.traffic.Furnizues;
import model.traffic.Klient;

public class Faktura {
    private SimpleStringProperty klient = new SimpleStringProperty();
    private SimpleStringProperty furnizues = new SimpleStringProperty();
    private SimpleStringProperty dataLeshimit = new SimpleStringProperty();
    private SimpleStringProperty dataSkadimit = new SimpleStringProperty();
    private SimpleStringProperty puntori = new SimpleStringProperty();
    private SimpleStringProperty malli = new SimpleStringProperty();

    public Faktura() {}

    public Faktura(String klient, String furnizues, String dataLeshimit, String dataSkadimit, String puntori, String malli) {
        this.klient.set(klient);
        this.furnizues.set(furnizues);
        this.dataLeshimit.set(dataLeshimit);
        this.dataSkadimit.set(dataSkadimit);
        this.puntori.set(puntori);
        this.malli.set(malli);
    }

    public String getKlient() {
        return klient.get();
    }

    public void setKlient(String klient) {
        this.klient.set(klient);
    }

    public String getFurnizues() {
        return furnizues.get();
    }

    public void setFurnizues(String furnizues) {
        this.furnizues.set(furnizues);
    }

    public String getDataLeshimit() {
        return dataLeshimit.get();
    }

    public void setDataLeshimit(String dataLeshimit) {
        this.dataLeshimit.set(dataLeshimit);
    }

    public String getDataSkadimit() {
        return dataSkadimit.get();
    }

    public void setDataSkadimit(String dataSkadimit) {
        this.dataSkadimit.set(dataSkadimit);
    }

    public String getPuntori() {
        return puntori.get();
    }

    public void setPuntori(String puntori) {
        this.puntori.set(puntori);
    }

    public String getMalli() {
        return malli.get();
    }

    public void setMalli(String malli) {
        this.malli.set(malli);
    }
}
