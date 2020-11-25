package model.raport;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Raporti {
    private SimpleStringProperty llojiRaportit;
    private SimpleIntegerProperty nrDokumentit;
    private SimpleStringProperty dataFillimi;
    private SimpleStringProperty dataMbarimi;
    private SimpleStringProperty pershkrimiRaporti;
    private SimpleStringProperty nrSerial;
    private SimpleStringProperty njesiSherbimi;
    private SimpleStringProperty statusPagese;

    public Raporti() {
        this.llojiRaportit = new SimpleStringProperty();
        this.nrDokumentit = new SimpleIntegerProperty();
        this.dataFillimi = new SimpleStringProperty();
        this.dataMbarimi = new SimpleStringProperty();
        this.pershkrimiRaporti = new SimpleStringProperty();
        this.nrSerial = new SimpleStringProperty();
        this.njesiSherbimi = new SimpleStringProperty();
        this.statusPagese = new SimpleStringProperty();
    }

    public Raporti(String llojiRaportit, int nrDokumentit, String dataFillimi, String dataMbarimi, String pershkrimiRaporti,
                   String nrSerial, String njesiSherbimi, String statusPagese) {
        this.llojiRaportit = new SimpleStringProperty(llojiRaportit);
        this.nrDokumentit = new SimpleIntegerProperty(nrDokumentit);
        this.dataFillimi = new SimpleStringProperty(dataFillimi);
        this.dataMbarimi = new SimpleStringProperty(dataMbarimi);
        this.pershkrimiRaporti = new SimpleStringProperty(pershkrimiRaporti);
        this.nrSerial = new SimpleStringProperty(nrSerial);
        this.njesiSherbimi = new SimpleStringProperty(njesiSherbimi);
        this.statusPagese = new SimpleStringProperty(statusPagese);
    }


    public SimpleStringProperty llojiRaportitProperty() {
        return llojiRaportit;
    }

    public SimpleIntegerProperty nrDokumentitProperty() {
        return nrDokumentit;
    }

    public SimpleStringProperty dataFillimiProperty() {
        return dataFillimi;
    }

    public SimpleStringProperty dataMbarimiProperty() {
        return dataMbarimi;
    }

    public SimpleStringProperty pershkrimiRaportiProperty() {
        return pershkrimiRaporti;
    }

    public SimpleStringProperty nrSerialProperty() {
        return nrSerial;
    }

    public SimpleStringProperty njesiSherbimiProperty() {
        return njesiSherbimi;
    }

    public SimpleStringProperty statusPageseProperty() {
        return statusPagese;
    }

    public String getLlojiRaportit() {
        return this.llojiRaportit.get();
    }

    public int getNrDokumentit() {
        return this.nrDokumentit.get();
    }

    public String getDataFillimi() {
        return this.dataFillimi.get();
    }

    public String getDataMbarimi() {
        return this.dataMbarimi.get();
    }

    public String getPershkrimiRaport() {
        return this.pershkrimiRaporti.get();
    }

    public String getNrSerial() {
        return this.nrSerial.get();
    }

    public String getNjesiSherbimi() {
        return this.njesiSherbimi.get();
    }

    public String getStatusPagese() {
        return this.statusPagese.get();
    }

    public void setLlojiRaportit(String llojiRaportit) {
        this.llojiRaportit.set(llojiRaportit);
    }

    public void setNrDokumentit(int nrDokumentit) {
        this.nrDokumentit.set(nrDokumentit);
    }

    public void setDataFillimi(String dataFillimi) {
        this.dataFillimi.set(dataFillimi);
    }

    public void setDataMbarimi(String dataMbarimi) {
        this.dataMbarimi.set(dataMbarimi);
    }

    public void setPershkrimiRaporti(String pershkrimiRaporti) {
        this.pershkrimiRaporti.set(pershkrimiRaporti);
    }

    public void setNrSerial(String nrSerial) {
        this.nrSerial.set(nrSerial);
    }

    public void setNjesiSherbimi(String njesiSherbimi) {
        this.njesiSherbimi.set(njesiSherbimi);
    }

    public void setStatusPagese(String statusPagese) {
        this.statusPagese.set(statusPagese);
    }

    @Override
    public String toString() {
        return "Raporti{" +
                "lloji=" + llojiRaportit +
                ", nrDokumentit=" + nrDokumentit +
                ", dataFillimi=" + dataFillimi +
                ", dataMbarimi=" + dataMbarimi +
                ", pershkrimi=" + pershkrimiRaporti +
                ", nrSerial=" + nrSerial +
                ", njesiSherbimi=" + njesiSherbimi +
                ", statusPagese=" + statusPagese +
                '}';
    }
}
