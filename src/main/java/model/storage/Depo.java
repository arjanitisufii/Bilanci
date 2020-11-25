package model.storage;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.items.Malli;

import java.util.ArrayList;
import java.util.List;

public class Depo {
    private SimpleStringProperty artikull = new SimpleStringProperty();
    private SimpleStringProperty seria = new SimpleStringProperty();
    private SimpleIntegerProperty sasia = new SimpleIntegerProperty();

    public Depo() {

    }

    public Depo(String artikull, String seria, int sasia) {
        this.artikull.set(artikull);
        this.seria.set(seria);
        this.sasia.set(sasia);
    }

    public String getArtikull() {
        return artikull.get();
    }

    public void setArtikull(String artikull) {
        this.artikull.set(artikull);
    }

    public String getSeria() {
        return seria.get();
    }

    public void setSeria(String seria) {
        this.seria.set(seria);
    }

    public int getSasia() {
        return sasia.get();
    }

    public void setSasia(int sasia) {
        this.sasia.set(sasia);
    }

    @Override
    public String toString() {
        return getArtikull();
    }
}
