package view.raport;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.raport.Raporti;
import model.storage.Depo;
import model.traffic.Klient;
import org.controlsfx.control.CheckComboBox;
import services.items.MalliData;
import services.mail.MailData;
import services.raport.FaktureData;
import services.raport.RaportiData;
import services.storage.DepoData;
import services.traffic.KlientiData;
import view.dashboard.DashboardController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PorosiShtijeController {

    private KlientiData klientiData = new KlientiData();
    private DepoData depoData = new DepoData();
    private MailData mailData = new MailData();
    private FaktureData faktureData = new FaktureData();
    private RaportiData raportiData = new RaportiData();


    @FXML
    private ChoiceBox<Klient> klientChoiceBox;
    @FXML
    private CheckComboBox<Depo> malliChoiceBox;
    @FXML
    private TextField agjenti;
    @FXML
    private TextField njesiSherbimi;
    @FXML
    private TextField nrDokumenti;
    @FXML
    private TextField dataFillimi;
    @FXML
    private TextField dataPageses;
    @FXML
    private TextField pershkrimi;
    @FXML
    private TextField nrSerial;
    @FXML
    private TextField lloji;
    @FXML
    private TextField pagesa;
    @FXML
    private TextField sasia;
    @FXML
    private Label warning;
    @FXML
    private AnchorPane anchor;

    public void initialize() {
        klientChoiceBox.getItems().addAll(klientiData.getData());
        malliChoiceBox.getItems().addAll(depoData.getDepoData());
    }

    @FXML
    public void getDataToInsert() {
        warning.setVisible(false);

        if (PorosiUtility.getInstance().validateData(njesiSherbimi.getText(), nrDokumenti.getText(), dataFillimi.getText(), dataPageses.getText(), pershkrimi.getText(), nrSerial.getText(), lloji.getText(), pagesa.getText(), sasia.getText(), agjenti.getText())) {
            if (klientChoiceBox.getSelectionModel().getSelectedItem() != null && malliChoiceBox.getCheckModel().getCheckedIndices().size() != 0) {
                warning.setVisible(false);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Porosi Shitje");
                alert.setContentText("Deshironi te vashdoni me kete porosi ?");
                alert.setHeaderText("Klient : " + klientChoiceBox.getSelectionModel().getSelectedItem().getEmri() + "\n" +
                        "Mallra : " + Arrays.toString(malliChoiceBox.getCheckModel().getCheckedItems().toArray()) + "\n" +
                        "Sasia :  " + sasia.getText());
                load(alert);
            } else {
                warning.setVisible(true);
                warning.setText("Plotesoni te gjitha te dhenat !");
            }
        } else {
            warning.setVisible(true);
            warning.setText("Plotesoni te gjitha te dhenat !");
        }
    }


    public void load(Alert alert) {
        Optional<ButtonType> buttonTypeDialog = alert.showAndWait();
        if (buttonTypeDialog.isPresent() && buttonTypeDialog.get() == ButtonType.OK) {
            faktureData.insertFakture("Bilanci", klientChoiceBox.getSelectionModel().getSelectedItem().getEmri(), dataFillimi.getText(), dataPageses.getText(), agjenti.getText(), Arrays.toString(malliChoiceBox.getCheckModel().getCheckedItems().toArray()));
            int sasiaConverted = Integer.parseInt(sasia.getText());
            List<Depo> depoList = malliChoiceBox.getCheckModel().getCheckedItems();
            for (Depo d : depoList) {
                int value = d.getSasia() - sasiaConverted;
                if (value < 0) {
                    Alert alert1 = new Alert(Alert.AlertType.WARNING);
                    alert1.setTitle("Mungese Malli");
                    alert1.setContentText("Nuk mund te procesohet porosia \nMalli i kerkuar eshte ne mungese");
                    alert1.showAndWait();
                    return;
                }

                depoData.updateSasia(value, d.getSeria());
                raportiData.insertData(lloji.getText(), Integer.parseInt(nrDokumenti.getText()), dataFillimi.getText(), dataPageses.getText(), pershkrimi.getText(), nrSerial.getText(), njesiSherbimi.getText(), pagesa.getText());
                Klient klient = klientChoiceBox.getSelectionModel().getSelectedItem();
                Dialog<ButtonType> dialog = PorosiUtility.getInstance().loadFakturaShitje(anchor, klient.getAdresa(), klient.getTel(), "" + klient.getNipt(), klient.getNrTVSH() + "", klient.getNrBiznesit(), klient.getNrLicenses(), klient.getEmri(), "" + Integer.parseInt(sasia.getText()), malliChoiceBox, klientChoiceBox);
                String content = "Keni bere porosine ne vijim : \n";
                String emri = "Klienti : " + klientChoiceBox.getSelectionModel().getSelectedItem().getEmri() + "\n";
                String porosia = "Mallra : " + Arrays.toString(malliChoiceBox.getCheckModel().getCheckedItems().toArray()) + "\n";
                String sasiaString = "Sasia : " + sasia.getText();
                StringBuilder stringBuilder = new StringBuilder(content).append(emri).append(porosia).append(sasiaString);
                dialog.showAndWait();
                mailData.generateAndSendEmail("Porosi Shitje", stringBuilder.toString(), "kokamasive@gmail.com");
                if (!dialog.isShowing()) {
                    PorosiUtility.getInstance().clearData(malliChoiceBox, agjenti, sasia, njesiSherbimi, nrDokumenti, lloji, dataFillimi, dataPageses, pershkrimi, nrSerial, pagesa);
                }
            }
        }
    }
}
