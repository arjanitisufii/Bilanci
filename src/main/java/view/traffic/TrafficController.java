package view.traffic;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import model.traffic.Klient;
import model.traffic.Traffic;

import java.io.IOException;

public class TrafficController {

    private static TrafficController instnace = new TrafficController();

    public static TrafficController getInstnace() {
        return instnace;
    }

    public Dialog<ButtonType> load(AnchorPane anchorPane , String title, String header, FXMLLoader fxmlLoader,String resource) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(anchorPane.getScene().getWindow());
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        fxmlLoader.setLocation(getClass().getResource(resource));
        dialog.getDialogPane().setContent(fxmlLoader.load());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        return dialog;
    }

    public boolean trafficSelected(Traffic traffic, String title, String header, String content) {
        if (traffic == null) {
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Selektoni " + title);
            alert.setHeaderText("Asnje " + header + " i selektuar");
            alert.setContentText("Per te bere ndryshime selektoni " + content);
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
