package view.dashboard;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.raport.Raporti;
import model.storage.Depo;
import services.employees.PuntoriData;
import services.raport.RaportiData;
import services.storage.DepoData;
import services.traffic.TrafficService;
import view.login.Main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class DashboardController {
    private DepoData depoData = new DepoData();
    private RaportiData raportiData = new RaportiData();


    @FXML
    public TableView<Depo> depo;
    @FXML
    private TableView<Raporti> raportiTableView;
    @FXML
    private VBox vBox;
    @FXML
    private TextField numerDokumenti;
    @FXML
    private DatePicker dataFillimi;
    @FXML
    private DatePicker dataMbarimi;
    @FXML
    private TextField pershkrimi;
    @FXML
    private TextField nrSerial;
    @FXML
    private TextField njesiSherbimi;
    @FXML
    private TextField lloji;
    @FXML
    private ChoiceBox<String> statusPagese;

    public void initialize() {
        depoData.deleteData();
        Task<ObservableList<Depo>> task = new Task<ObservableList<Depo>>() {
            @Override
            protected ObservableList<Depo> call() throws Exception {
                return FXCollections.observableArrayList(depoData.getDepoData());
            }
        };

        depo.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    public void refreshTable() {
        initialize();
    }



    @FXML
    public void showPuntoretMenu() {
        loadMenuItems("Te dhenat e Puntoreve", "src/main/resources/employees/employeesPuntori.fxml");
    }

    @FXML
    public void showKlientetMenu() {
        loadMenuItems("Te dhenat e Klienteve", "src/main/resources/traffic/trafficKlienti.fxml");
    }

    @FXML
    public void showFurnizuesitMenu() {
        loadMenuItems("Te dhenat e Furnizuesve", "src/main/resources/traffic/trafficFurnizuesi.fxml");
    }

    @FXML
    public void showDepoData() {
        loadMenuItems("Te dhenat e Depos", "src/main/resources/storage/depoData.fxml");
    }

    @FXML
    public void changePassword() throws IOException {
        Stage stage = (Stage) vBox.getScene().getWindow();
        String title = stage.getTitle();
        String userName = title.substring(10);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Label oldPassword = new Label("Old Password");
        oldPassword.setStyle("-fx-text-alignment: center");
        oldPassword.setStyle("-fx-font-weight: bold");

        Label newPassword = new Label("New Password");
        newPassword.setStyle("-fx-text-alignment: center");
        newPassword.setStyle("-fx-font-weight: bold");

        Label confirmNewPassword = new Label("Confirm New Password");
        confirmNewPassword.setStyle("-fx-text-alignment: center");
        confirmNewPassword.setStyle("-fx-font-weight: bold");

        gridPane.add(oldPassword, 0, 1);
        gridPane.add(newPassword, 0, 2);
        gridPane.add(confirmNewPassword, 0, 3);

        TextField oldPasswordText = new TextField();
        PasswordField newPasswordField = new PasswordField();
        PasswordField confirmNewPasswordField = new PasswordField();


        gridPane.add(oldPasswordText, 1, 1);
        gridPane.add(newPasswordField, 1, 2);
        gridPane.add(confirmNewPasswordField, 1, 3);
        gridPane.setAlignment(Pos.CENTER);
        Label warning = new Label("Fjalekalimi i vjeter i pasakte !");
        warning.setTextFill(Color.RED);
        warning.setVisible(false);
        gridPane.add(warning, 0, 4);
        Label warning2 = new Label("Fjalekalimet nuk jane te sakta !");
        warning2.setTextFill(Color.RED);
        warning2.setVisible(false);
        gridPane.add(warning2, 0, 4);

        Button ok = new Button("OK");
        gridPane.add(ok, 1, 4);
        gridPane.setHalignment(ok, HPos.RIGHT);

        PuntoriData puntoriData = new PuntoriData();

        Scene scene = new Scene(gridPane, 350, 200);
        Stage stage1 = new Stage();
        stage1.setTitle("Nderroni Fjalekalimin");
        stage1.setScene(scene);
        stage1.show();

        ok.setOnAction(mouseEvent -> {
            warning.setVisible(false);
            warning2.setVisible(false);
            String oldPasswordTextField = oldPasswordText.getText();
            String oldPasswordOfUser = puntoriData.getPasswordByName(userName);
            String newPasswordOfUser = newPasswordField.getText();
            String confirmNewPasswordOfUser = confirmNewPasswordField.getText();

            if (oldPasswordTextField.equalsIgnoreCase(oldPasswordOfUser)) {
                if (newPasswordOfUser.trim().length() != 0 && confirmNewPasswordOfUser.trim().length() != 0 && newPasswordOfUser.equalsIgnoreCase(confirmNewPasswordOfUser)) {
                    puntoriData.updatePassword(newPasswordOfUser,userName);
                } else {
                    warning2.setVisible(true);
                    return;
                }
            } else {
                warning.setVisible(true);
                return;
            }
            stage1.close();
        });
    }

    @FXML
    public void goToLogin() throws Exception {
        vBox.getScene().getWindow().hide();
        Main main = new Main();
        Stage stage = new Stage();
        main.start(stage);
    }

    @FXML
    public void clearFields() {
        numerDokumenti.clear();
        dataFillimi.getEditor().clear();
        dataMbarimi.getEditor().clear();
        pershkrimi.clear();
        nrSerial.clear();
        njesiSherbimi.clear();
        lloji.clear();
        statusPagese.getSelectionModel().clearSelection();
        raportiTableView.getItems().clear();
    }

    @FXML
    public void searchData() {
        int nrDokumentit = (int)TrafficService.getInstance().stringToLong(numerDokumenti.getText());
        String dFillimi = "";
        String dMbarimi = "";
        if(dataFillimi.getValue() != null) {
            dFillimi = dataFillimi.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        if(dataMbarimi.getValue() != null) {
            dMbarimi+= " " + dataMbarimi.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        System.out.println(dFillimi);
        System.out.println(dMbarimi);

        List<Raporti> list = raportiData.searchData(nrDokumentit,dFillimi,dMbarimi,pershkrimi.getText(),nrSerial.getText(),njesiSherbimi.getText(),lloji.getText(),statusPagese.getValue());
        Task<ObservableList<Raporti>> task = new Task<ObservableList<Raporti>>() {
            @Override
            protected ObservableList<Raporti> call() throws Exception {
                return FXCollections.observableArrayList(list);
            }
        };

        raportiTableView.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    public void exitPlatform() {
        Platform.exit();
    }

    private void loadMenuItems(String titleName, String path) {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            URL url = new File(path).toURI().toURL();
            dialog.setTitle(titleName);
            dialog.getDialogPane().setContent(FXMLLoader.load(url));
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
            dialog.showAndWait();
        } catch (IOException r) {
            System.out.println("IOException on loadMenuItems() : " + r.getMessage());
            r.printStackTrace();
        }
    }

    @FXML
    public void porosiShitje() throws IOException{
        loadMenuItems("Porosi Shitje","src/main/resources/raport/porosiShitje.fxml");
    }

    @FXML
    public void porosiBlerje() {
        loadMenuItems("Porosi Blerje","src/main/resources/raport/porosiBlerje.fxml");
    }
}

