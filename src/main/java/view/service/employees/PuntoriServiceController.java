package view.service.employees;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.employees.Puntori;
import services.employees.PuntoriData;
import services.traffic.TrafficService;
import services.utility.ServiceUtility;

public class PuntoriServiceController {
    private PuntoriData puntoriData = new PuntoriData();

    @FXML
    private TextField emri;
    @FXML
    private TextField mbiemri;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldConfirm;
    @FXML
    private Label warning;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button shto;


    @FXML
    public Puntori addPuntor() {
        String name = emri.getText();
        String surname = mbiemri.getText();
        String puntoriEmail = email.getText();
        String telefoni = tel.getText();
        String pass = passwordField.getText();

        if (telefoni.trim().length() == 0) {
            warning.setVisible(true);
            warning.setText("Plotesoni te gjitha te dhenat e kerkuara ");
            return null;
        }
        warning.setVisible(false);

        long telefoniLong = TrafficService.getInstance().stringToLong(telefoni);

        if (!getWarnings(name, surname, puntoriEmail, telefoniLong, pass)) {
            return null;
        }

        anchor.getScene().getWindow().hide();
        return new Puntori(name, surname, puntoriEmail, telefoniLong, 0, pass);
    }

    public boolean edit(Puntori puntori) {
        long telephone = TrafficService.getInstance().stringToLong(tel.getText());

        if (!getEditWarnings(emri.getText(), mbiemri.getText(), email.getText(), telephone, passwordFieldConfirm.getText())) {
            return false;
        }

        puntori.setEmri(emri.getText());
        puntori.setMbiemri(mbiemri.getText());
        puntori.setEmail(email.getText());
        puntori.setTel(telephone);
        puntori.setPassword(passwordFieldConfirm.getText());
        return true;
    }

    public void getPuntoriToEdit(Puntori puntori) {
        passwordField.setDisable(true);
        String pass1 = puntoriData.getPasswordByName(puntori.getEmri());
        shto.setVisible(false);
        emri.setText(puntori.getEmri());
        mbiemri.setText(puntori.getMbiemri());
        email.setText(puntori.getEmail());
        tel.setText("" + puntori.getTel());
        passwordFieldConfirm.setText(pass1);
    }

    private boolean getWarnings(String name, String surname, String email, long tel, String pass) {
        warning.setVisible(false);
        if (!ServiceUtility.getInstance().validate(name, surname, email, tel, 0, pass)) {
            warning.setVisible(true);
            warning.setText("Plotesoni te gjitha te dhenat e kerkuara !");
            return false;
        } else if (!pass.equalsIgnoreCase(passwordFieldConfirm.getText())) {
            warning.setVisible(true);
            warning.setText("Fjalekalimet nuk jane te njejta !");
            passwordField.clear();
            passwordFieldConfirm.clear();
            return false;
        }
        return true;
    }

    private boolean getEditWarnings(String name, String surname, String email, long tel, String pass) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (!ServiceUtility.getInstance().validate(name, surname, email, tel, 0, pass)) {
            alert.setTitle("Verejtje");
            alert.setHeaderText("Plotesoni te gjitha te dhenat e kerkuara !");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
