package view.raport;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.items.Malli;
import model.traffic.Furnizues;
import org.controlsfx.control.CheckComboBox;
import services.items.MalliData;
import services.mail.MailData;
import services.raport.FaktureData;
import services.raport.RaportiData;
import services.storage.DepoData;
import services.traffic.FurnizuesData;
import view.dashboard.DashboardController;


import java.util.*;

public class PorosiBlerjeController {
    private FurnizuesData furnizuesData = new FurnizuesData();
    private MalliData malliData = new MalliData();
    private RaportiData raportiData = new RaportiData();
    private MailData mailData = new MailData();
    private DepoData depoData = new DepoData();
    private FaktureData faktureData = new FaktureData();

    @FXML
    private ChoiceBox<Furnizues> furnizuesChoiceBox;
    @FXML
    private CheckComboBox<Malli> malliCheckComboBox;
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
        furnizuesChoiceBox.getItems().addAll(furnizuesData.getData());
        warning.setVisible(false);
    }

    @FXML
    public void addMalliBasedOnFurnizuesi() {
        malliCheckComboBox.getItems().clear();
        malliCheckComboBox.getCheckModel().clearChecks();
        String emri = furnizuesChoiceBox.getSelectionModel().getSelectedItem().getEmri();
        if (!furnizuesChoiceBox.getItems().isEmpty()) {
            malliCheckComboBox.getItems().addAll(malliData.queryMalliDataByFurnizuesiName(emri));
        }
    }

    @FXML
    public void getDataToInsert() {
        warning.setVisible(false);

        if (PorosiUtility.getInstance().validateData(njesiSherbimi.getText(), nrDokumenti.getText(), dataFillimi.getText(), dataPageses.getText(), pershkrimi.getText(), nrSerial.getText(), lloji.getText(), pagesa.getText(), sasia.getText(), agjenti.getText())) {
            if (furnizuesChoiceBox.getSelectionModel().getSelectedItem() != null && malliCheckComboBox.getCheckModel().getCheckedIndices().size() != 0) {
                warning.setVisible(false);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Porosi Blerje");
                alert.setContentText("Deshironi te vashdoni me kete porosi ?");
                alert.setHeaderText("Furnizues : " + furnizuesChoiceBox.getSelectionModel().getSelectedItem().getEmri() + "\n" +
                        "Mallra :\t  " + Arrays.toString(malliCheckComboBox.getCheckModel().getCheckedItems().toArray()) + "\n" +
                        "Sasia :\t  " + sasia.getText());
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
            faktureData.insertFakture(furnizuesChoiceBox.getSelectionModel().getSelectedItem().getEmri(), "Bilanci", dataFillimi.getText(), dataPageses.getText(), agjenti.getText(), Arrays.toString(malliCheckComboBox.getCheckModel().getCheckedItems().toArray()));

            int sasiaConverted = Integer.parseInt(sasia.getText());
            List<Malli> malliList = malliCheckComboBox.getCheckModel().getCheckedItems();
            for (Malli d : malliList) {
                int value = depoData.getSasia(d.getSeria()) + sasiaConverted;
                depoData.insertData(d.getPershkrimi(), d.getSeria(), value);
            }
            raportiData.insertData(lloji.getText(), Integer.parseInt(nrDokumenti.getText()), dataFillimi.getText(), dataPageses.getText(), pershkrimi.getText(), nrSerial.getText(), njesiSherbimi.getText(), pagesa.getText());
            Dialog<ButtonType> dialog = PorosiUtility.getInstance().loadFakturaBlerje(anchor, "Hajvali", "04420203", "334323", "34483", "A594", "A549493", "Bilanci", "" + Integer.parseInt(sasia.getText()), malliCheckComboBox, furnizuesChoiceBox);
            String content = "Keni bere porosine ne vijim : \n";
            String emri = "Klienti : " + furnizuesChoiceBox.getSelectionModel().getSelectedItem().getEmri() + "\n";
            String porosia = "Mallra : " + Arrays.toString(malliCheckComboBox.getCheckModel().getCheckedItems().toArray()) + "\n";
            String sasiaString = "Sasia : " + sasia.getText();
            StringBuilder stringBuilder = new StringBuilder(content).append(emri).append(porosia).append(sasiaString);
            dialog.showAndWait();
            mailData.generateAndSendEmail("Porosi Blerje", stringBuilder.toString(), furnizuesChoiceBox.getSelectionModel().getSelectedItem().getEmail());
            if (!dialog.isShowing()) {
                PorosiUtility.getInstance().clearData(malliCheckComboBox, agjenti, sasia, njesiSherbimi, nrDokumenti, lloji, dataFillimi, dataPageses, pershkrimi, nrSerial, pagesa);
            }
        }
    }
}