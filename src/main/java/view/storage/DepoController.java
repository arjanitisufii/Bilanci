package view.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import model.storage.Depo;
import services.storage.DepoData;


public class DepoController {
    private DepoData depoData = new DepoData();

    @FXML
    private TableView<Depo> depoTableView;

    public void initialize() {
        Task<ObservableList<Depo>> task = new Task<ObservableList<Depo>>() {
            @Override
            protected ObservableList<Depo> call() throws Exception {
                return FXCollections.observableArrayList(depoData.getDepoData());
            }
        };

        depoTableView.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }


}
