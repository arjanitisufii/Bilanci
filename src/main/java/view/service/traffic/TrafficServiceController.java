package view.service.traffic;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.traffic.Traffic;
import services.traffic.TrafficService;

public class TrafficServiceController {
    @FXML
    private TextField emri;
    @FXML
    private TextField adresa;
    @FXML
    private TextField email;
    @FXML
    private TextField nipt;
    @FXML
    private TextField telefoni;
    @FXML
    private TextField nrTVSH;
    @FXML
    private TextField nrLicenses;
    @FXML
    private TextField nrBiznesit;
    @FXML
    private Label warning;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button shto;

    @FXML
    public Traffic shtoTraffic() {
        if (!validate()) {
            return null;
        }

        anchor.getScene().getWindow().hide();
        return new Traffic(emri.getText(), adresa.getText(), telefoni.getText(),
                email.getText(), Long.parseLong(nipt.getText()), Long.parseLong(nrTVSH.getText()),
                nrBiznesit.getText(), nrLicenses.getText());
    }

    public boolean validate() {
        warning.setVisible(false);
        if (nipt.getText().trim().length() == 0 || nrTVSH.getText().trim().length() == 0) {
            warning.setVisible(true);
            warning.setText("Nipt dhe TVSH duhet te permbajne \nvetem numra\"");
            return false;
        }
        long niptLong = TrafficService.getInstance().stringToLong(nipt.getText());
        long nrTvshLong = TrafficService.getInstance().stringToLong(nrTVSH.getText());

        if (!TrafficService.getInstance().validateData(emri.getText(), adresa.getText(), telefoni.getText(), email.getText(), niptLong, nrTvshLong, nrBiznesit.getText(), nrLicenses.getText())) {
            warning.setVisible(true);
            warning.setText("Plotesoni te gjitha te dhenat e kerkuara ! \n\"" +
                    "Nipt dhe TVSH duhet te permbajne vetem numra\"");
            return false;
        }
        return true;
    }

    public boolean validateEdit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (nipt.getText().trim().length() == 0 || nrTVSH.getText().trim().length() == 0) {
            alert.setTitle("Verejtje");
            alert.setHeaderText("Nipt dhe TVSH duhet te permbajne vetem numra");
            alert.showAndWait();
            return false;
        }
        long niptLong = TrafficService.getInstance().stringToLong(nipt.getText());
        long nrTvshLong = TrafficService.getInstance().stringToLong(nrTVSH.getText());

        if (!TrafficService.getInstance().validateData(emri.getText(), adresa.getText(), telefoni.getText(), email.getText(), niptLong, nrTvshLong, nrBiznesit.getText(), nrLicenses.getText())) {
            alert.setTitle("Verejtje");
            alert.setHeaderText("Plotesoni te gjitha te dhenat e kerkuara ! \n" +
                    "Nipt dhe TVSH duhet te permbajne vetem numra");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    public void getContactToEdit(Traffic traffic) {
        anchor.getChildren().remove(shto);
        emri.setText(traffic.getEmri());
        adresa.setText(traffic.getAdresa());
        telefoni.setText(traffic.getTel());
        email.setText(traffic.getEmail());
        nipt.setText("" + traffic.getNipt());
        nrTVSH.setText("" + traffic.getNrTVSH());
        nrBiznesit.setText(traffic.getNrBiznesit());
        nrLicenses.setText(traffic.getNrLicenses());
    }


    public boolean edit(Traffic traffic) {
        if (!validateEdit()) {
            return false;
        }

        traffic.setEmri(emri.getText());
        traffic.setAdresa(adresa.getText());
        traffic.setTel(telefoni.getText());
        traffic.setEmail(email.getText());
        traffic.setNipt(Long.parseLong(nipt.getText()));
        traffic.setNrTVSH(Long.parseLong(nrTVSH.getText()));
        traffic.setNrBiznesit(nrBiznesit.getText());
        traffic.setNrLicenses(nrLicenses.getText());
        return true;
    }
}
