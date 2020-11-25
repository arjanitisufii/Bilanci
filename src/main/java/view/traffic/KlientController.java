package view.traffic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.traffic.Klient;
import model.traffic.Traffic;
import services.traffic.KlientiData;

import view.service.traffic.TrafficServiceController;

import java.io.IOException;
import java.util.Optional;

public class KlientController {
    private KlientiData klientiData = new KlientiData();
    @FXML
    private AnchorPane anchorKlient;
    @FXML
    private TableView<Klient> klientet;

    public void initialize() {
        Task<ObservableList<Klient>> task = new Task<ObservableList<Klient>>() {
            @Override
            protected ObservableList<Klient> call() throws Exception {
                return FXCollections.observableArrayList(klientiData.getData());
            }
        };

        klientet.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    public void shtoKlient() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        String resource = "/service/trafficService.fxml";
        Dialog<ButtonType> dialog = TrafficController.getInstnace().load(anchorKlient, "Shto Klient", "Shtoni informatat per klientin e ri", fxmlLoader,resource);
        TrafficServiceController trafficServiceController = fxmlLoader.getController();
        dialog.getDialogPane().getButtonTypes().remove(ButtonType.OK);
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.CANCEL) {
            return;
        }

        Traffic traffic = trafficServiceController.shtoTraffic();
        if (traffic == null) {
            shtoKlient();
        }

        Klient klient = new Klient(traffic.getEmri(), traffic.getAdresa(), traffic.getTel(), traffic.getEmail(), traffic.getNipt(), traffic.getNrTVSH(), traffic.getNrBiznesit(), traffic.getNrLicenses());
        klientiData.addKlient(klient);
        initialize();
    }

    @FXML
    public void editKlient() throws IOException {
        Klient klient = klientet.getSelectionModel().getSelectedItem();
        if (!TrafficController.getInstnace().trafficSelected(klient,"Klientin", "klient", "klientin")) {
            return;
        }

        int id = klientiData.getIdByNrBiznesit(klient.getNrBiznesit());
        FXMLLoader fxmlLoader = new FXMLLoader();
        String resource = "/service/trafficService.fxml";
        Dialog<ButtonType> dialog = TrafficController.getInstnace().load(anchorKlient, "Edit Klient", "Ndryshoni informatat per klientin", fxmlLoader, resource);
        TrafficServiceController trafficServiceController = fxmlLoader.getController();
        trafficServiceController.getContactToEdit(klient);
        Optional<ButtonType> optionalButtonType = dialog.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.OK) {
            if(trafficServiceController.edit(klient)) {
                klientiData.editKlient(klient,id);
                initialize();
            } else {
                editKlient();
            }
        }
    }

    @FXML
    public void deleteKlient() {
        Klient klient = klientet.getSelectionModel().getSelectedItem();
        if(!TrafficController.getInstnace().trafficSelected(klient,"Klientin", "klient", "klientin")) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Jeni i sigurt qe deshironi ta largoni klientin : " + klient.getEmri());
        Optional<ButtonType> dialog = alert.showAndWait();
        if(dialog.isPresent() && dialog.get() == ButtonType.OK) {
            klientiData.deleteKlient(klient.getNrBiznesit());
            initialize();
        }
    }
}
