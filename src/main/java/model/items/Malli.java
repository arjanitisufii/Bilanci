package model.items;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Malli {
    private SimpleLongProperty kodi = new SimpleLongProperty();
    private SimpleStringProperty pershkrimi = new SimpleStringProperty();
    private SimpleStringProperty seria = new SimpleStringProperty();
    private SimpleStringProperty dataSkadimit = new SimpleStringProperty();
    private SimpleStringProperty njesia = new SimpleStringProperty();
    private SimpleIntegerProperty sasia = new SimpleIntegerProperty();
    private SimpleDoubleProperty cmimiPaTVSH = new SimpleDoubleProperty();
    private SimpleDoubleProperty cmimiMeTVSH = new SimpleDoubleProperty();
    private SimpleDoubleProperty rabati = new SimpleDoubleProperty();
    private SimpleDoubleProperty tvsh = new SimpleDoubleProperty();
    private SimpleDoubleProperty vleraMeTVSH = new SimpleDoubleProperty();

    public Malli() {
    }

    public Malli(long kodi, String pershkrimi, String seria, String dataSkadimit, String njesia,
                 int sasia, double cmimiPaTVSH, double cmimiMeTVSH, double rabati, double tvsh, double vleraMeTVSH) {
        this.kodi.set(kodi);
        this.pershkrimi.set(pershkrimi);
        this.seria.set(seria);
        this.dataSkadimit.set(dataSkadimit);
        this.njesia.set(njesia);
        this.sasia.set(sasia);
        this.cmimiPaTVSH.set(cmimiPaTVSH);
        this.cmimiMeTVSH.set(cmimiMeTVSH);
        this.rabati.set(rabati);
        this.tvsh.set(tvsh);
        this.vleraMeTVSH.set(vleraMeTVSH);
    }

    public long getKodi() {
        return kodi.get();
    }

    public void setKodi(long kodi) {
        this.kodi.set(kodi);
    }

    public String getPershkrimi() {
        return pershkrimi.get();
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi.set(pershkrimi);
    }

    public String getSeria() {
        return seria.get();
    }

    public void setSeria(String seria) {
        this.seria.set(seria);
    }

    public String getDataSkadimit() {
        return dataSkadimit.get();
    }

    public void setDataSkadimit(String dataSkadimit) {
        this.dataSkadimit.set(dataSkadimit);
    }

    public String getNjesia() {
        return njesia.get();
    }

    public void setNjesia(String njesia) {
        this.njesia.set(njesia);
    }

    public int getSasia() {
        return sasia.get();
    }

    public void setSasia(int sasia) {
        this.sasia.set(sasia);
    }

    public double getCmimiPaTVSH() {
        return cmimiPaTVSH.get();
    }

    public void setCmimiPaTVSH(double cmimiPaTVSH) {
        this.cmimiPaTVSH.set(cmimiPaTVSH);
    }

    public double getCmimiMeTVSH() {
        return cmimiMeTVSH.get();
    }


    public void setCmimiMeTVSH(double cmimiMeTVSH) {
        this.cmimiMeTVSH.set(cmimiMeTVSH);
    }

    public double getRabati() {
        return rabati.get();
    }

    public void setRabati(double rabati) {
        this.rabati.set(rabati);
    }

    public double getTvsh() {
        return tvsh.get();
    }


    public void setTvsh(double tvsh) {
        this.tvsh.set(tvsh);
    }

    public double getVleraMeTVSH() {
        return vleraMeTVSH.get();
    }

    public void setVleraMeTVSH(double vleraMeTVSH) {
        this.vleraMeTVSH.set(vleraMeTVSH);
    }

    @Override
    public String toString() {
        return pershkrimi.get();
    }
}
