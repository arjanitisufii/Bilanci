package view.employees;

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
import model.employees.Puntori;
import services.employees.EmployeesService;
import services.employees.PuntoriData;
import view.service.employees.PuntoriServiceController;
import view.traffic.TrafficController;

import java.io.IOException;
import java.util.Optional;

public class PuntoriController {
    private PuntoriData puntoriData = new PuntoriData();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Puntori> puntoret;

    public void initialize() {
        Task<ObservableList<Puntori>> task = new Task<ObservableList<Puntori>>() {
            @Override
            protected ObservableList<Puntori> call() throws Exception {
                return FXCollections.observableArrayList(puntoriData.getQueryData());
            }
        };

        puntoret.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    public void shtoPunetor() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        String resource = "/service/shtoPuntoriService.fxml";
        Dialog<ButtonType> dialog = TrafficController.getInstnace().load(anchorPane, "Shto Puntore", "Shtoni informatat per puntorin e ri", fxmlLoader, resource);
        dialog.getDialogPane().getButtonTypes().remove(ButtonType.OK);
        dialog.showAndWait();
        if (ButtonType.CANCEL == dialog.getResult()) {
            return;
        }
        PuntoriServiceController puntoriServiceController = fxmlLoader.getController();
        Puntori puntori = puntoriServiceController.addPuntor();
        if (puntori == null) {
            shtoPunetor();
        }
        EmployeesService.getInstance().addPuntori(puntori);
        initialize();
    }

    @FXML
    public void editPuntorin() throws IOException {
        Puntori puntori = puntoret.getSelectionModel().getSelectedItem();
        if(!selectedPuntor(puntori)) {
            return;
        }
        int id = puntoriData.getIdByName(puntori.getEmri());
        FXMLLoader fxmlLoader = new FXMLLoader();
        String resource = "/service/shtoPuntoriService.fxml";
        Dialog<ButtonType> dialog = TrafficController.getInstnace().load(anchorPane, "Shto Puntore", "Shtoni informatat per puntorin e ri", fxmlLoader, resource);
        PuntoriServiceController puntoriServiceController = fxmlLoader.getController();
        puntoriServiceController.getPuntoriToEdit(puntori);
        Optional<ButtonType> optionalButtonType = dialog.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.OK) {
            if (puntoriServiceController.edit(puntori)) {
                EmployeesService.getInstance().editPuntori(id, puntori);
                initialize();
            } else {
                editPuntorin();
            }
        }
    }

    @FXML
    public void deletePuntorin() {
        Puntori puntori = puntoret.getSelectionModel().getSelectedItem();
        if(!selectedPuntor(puntori)) {
            return;
        }

        int id = puntoriData.getIdByName(puntori.getEmri());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Jeni i sigurt qe deshironi ta largoni puntorin : " + puntori.getEmri());
        Optional<ButtonType> dialog = alert.showAndWait();
        if(dialog.isPresent() && dialog.get() == ButtonType.OK) {
            EmployeesService.getInstance().deletePuntori(id);
            initialize();
        }
    }

    private boolean selectedPuntor(Puntori puntori) {
        if (puntori == null) {
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setTitle("Selektoni Puntorin");
            alert.setHeaderText("Asnje puntor i selektuar");
            alert.setContentText("Per te bere ndryshime selektoni puntorin");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}