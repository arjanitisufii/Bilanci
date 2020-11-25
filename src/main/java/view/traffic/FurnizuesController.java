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
import model.traffic.Furnizues;
import model.traffic.Traffic;
import services.traffic.FurnizuesData;
import services.traffic.TrafficService;
import view.service.traffic.TrafficServiceController;

import java.io.IOException;
import java.util.Optional;

public class FurnizuesController {
    private FurnizuesData furnizuesData = new FurnizuesData();


    @FXML
    private TableView<Furnizues> furnizuesit;
    @FXML
    private AnchorPane anchorFurnizuesi;


    public void initialize() {
        Task<ObservableList<Furnizues>> furnizesTask = new Task<ObservableList<Furnizues>>() {
            @Override
            protected ObservableList<Furnizues> call() throws Exception {
                return FXCollections.observableArrayList(furnizuesData.getData());
            }
        };

        furnizuesit.itemsProperty().bind(furnizesTask.valueProperty());
        new Thread(furnizesTask).start();
    }

    @FXML
    public void shtoFurnizues() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        String resource = "/service/trafficService.fxml";
        Dialog<ButtonType> dialog = TrafficController.getInstnace().load(anchorFurnizuesi, "Shto Furnizues", "Shtoni informatat per furnizuesin e ri", fxmlLoader,resource);
        TrafficServiceController trafficServiceController = fxmlLoader.getController();
        dialog.getDialogPane().getButtonTypes().remove(ButtonType.OK);
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.CANCEL) {
            return;
        }

        Traffic traffic = trafficServiceController.shtoTraffic();
        if (traffic == null) {
            shtoFurnizues();
        }

        Furnizues furnizues = new Furnizues(traffic.getEmri(), traffic.getAdresa(), traffic.getTel(), traffic.getEmail(), traffic.getNipt(), traffic.getNrTVSH(), traffic.getNrBiznesit(), traffic.getNrLicenses());
        furnizuesData.addFurnizues(furnizues);
        initialize();
    }

    @FXML
    public void editFurnizues() throws IOException {
        Furnizues furnizues = furnizuesit.getSelectionModel().getSelectedItem();
        if(!TrafficController.getInstnace().trafficSelected(furnizues,"Furnizuesin", "furnizues", "furnizuesin")) {
            return;
        }

        int id = furnizuesData.getIdByBiz(furnizues.getNrBiznesit());
        FXMLLoader fxmlLoader = new FXMLLoader();
        String resource = "/service/trafficService.fxml";
        Dialog<ButtonType> dialog = TrafficController.getInstnace().load(anchorFurnizuesi, "Edit Furnizuesi", "Ndryshoni informatat per furnizuesin", fxmlLoader, resource);
        TrafficServiceController trafficServiceController = fxmlLoader.getController();
        trafficServiceController.getContactToEdit(furnizues);
        Optional<ButtonType> optionalButtonType = dialog.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.OK) {
            if (trafficServiceController.edit(furnizues)) {
                furnizuesData.editFurnizues(furnizues, id);
                initialize();
            } else {
                editFurnizues();
            }
        }
    }

    @FXML
    public void deleteFurnizues() {
        Furnizues furnizues = furnizuesit.getSelectionModel().getSelectedItem();
        if(!TrafficController.getInstnace().trafficSelected(furnizues,"Furnizuesin", "furnizues", "furnizuesin")) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Jeni i sigurt qe deshironi ta largoni furnizuesin : " + furnizues.getEmri());
        Optional<ButtonType> dialog = alert.showAndWait();
        if(dialog.isPresent() && dialog.get() == ButtonType.OK) {
            furnizuesData.deleteFurnizues(furnizues.getNrBiznesit());
            initialize();
        }
    }
}
