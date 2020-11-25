package view.raport;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.items.Malli;
import model.storage.Depo;
import model.traffic.Klient;
import model.traffic.Traffic;
import org.controlsfx.control.CheckComboBox;
import view.traffic.TrafficController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PorosiUtility {

    private static PorosiUtility instance = new PorosiUtility();

    public static  PorosiUtility getInstance() {
        return instance;
    }

    public boolean validateData(String njesiSherbimi, String nrDokumentiText, String dataFillimi, String dataMbarimi, String pershkrimi, String nrSerial, String lloji, String pagesa, String sasia, String agjenti) {
        List<String> list = new ArrayList<>();
        list.add(njesiSherbimi);
        list.add(nrDokumentiText);
        list.add(dataFillimi);
        list.add(dataMbarimi);
        list.add(pershkrimi);
        list.add(nrSerial);
        list.add(lloji);
        list.add(pagesa);
        list.add(sasia);
        list.add(agjenti);

        for (String word : list) {
            if (word == null || word.trim().length() == 0) {
                return false;
            }
        }

        int count = 0;
        for (int i = 0; i < nrDokumentiText.length(); i++) {
            if (Character.isDigit(nrDokumentiText.charAt(i))) {
                count++;
            }
        }
        int countSasia = 0;
        for (int i = 0; i < sasia.length(); i++) {
            if (Character.isDigit(sasia.charAt(i))) {
                countSasia++;
            }
        }

        return count == nrDokumentiText.length() && countSasia == sasia.length();
    }


    private Dialog<ButtonType> prepareLoad(AnchorPane anchor, String adresa, String tel, String nipt, String tvsh, String biznesi, String licensa, String emri, String sasia, CheckComboBox depoCheckComboBox, ChoiceBox choiceBox, boolean value) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            String resource = "/raport/faktura.fxml";
            Dialog<ButtonType> dialog = TrafficController.getInstnace().load(anchor, "Detajet e porosise", "", fxmlLoader, resource);
            dialog.getDialogPane().getButtonTypes().remove(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.FINISH);

            PrintFakturaController printFakturaController = fxmlLoader.getController();
            if(value) {
                printFakturaController.fillData((Traffic) choiceBox.getSelectionModel().getSelectedItem(), adresa, tel, nipt, tvsh, biznesi, licensa, emri);
                printFakturaController.getQmimiBlerje(depoCheckComboBox.getCheckModel().getCheckedItems(), Integer.parseInt(sasia));
            } else {
                printFakturaController.fillData(new Klient("Bilanci","Bregu i Diellit 2","4493992","bilanci@gmail.com",38383,3332,"A44332","A44993"), adresa, tel, nipt, tvsh, biznesi, licensa, emri);
                printFakturaController.getQmimiShitje(depoCheckComboBox.getCheckModel().getCheckedItems(), Integer.parseInt(sasia));
            }
            return dialog;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Dialog<ButtonType> loadFakturaBlerje(AnchorPane anchor, String adresa, String tel, String nipt, String tvsh, String biznesi, String licensa, String emri, String sasia, CheckComboBox<Malli> malliCheckComboBox, ChoiceBox choiceBox) {
        return prepareLoad( anchor,  adresa,  tel,  nipt,  tvsh,  biznesi,  licensa,  emri,  sasia,  malliCheckComboBox,  choiceBox, true);
    }

    public Dialog<ButtonType> loadFakturaShitje(AnchorPane anchor, String adresa, String tel, String nipt, String tvsh, String biznesi, String licensa, String emri, String sasia, CheckComboBox<Depo> depoCheckComboBox, ChoiceBox choiceBox) {
        return prepareLoad( anchor,  adresa,  tel,  nipt,  tvsh,  biznesi,  licensa,  emri,  sasia,  depoCheckComboBox,  choiceBox, false);
    }

    public void clearData(CheckComboBox malliCheckComboBox, TextField agjenti, TextField sasia, TextField njesiSherbimi, TextField nrDokumenti, TextField lloji,
                           TextField dataFillimi, TextField dataPageses, TextField pershkrimi, TextField nrSerial, TextField pagesa) {
        malliCheckComboBox.getCheckModel().clearChecks();
        agjenti.clear();
        sasia.clear();
        njesiSherbimi.clear();
        nrDokumenti.clear();
        lloji.clear();
        dataFillimi.clear();
        dataPageses.clear();
        pershkrimi.clear();
        nrSerial.clear();
        njesiSherbimi.clear();
        pagesa.clear();
    }
}
