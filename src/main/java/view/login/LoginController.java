package view.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.utility.LoginVerification;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LoginController {
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label incorrect;

    private static LoginController instance = new LoginController();


    public static LoginController getInstance() {
        return instance;
    }

    @FXML
    public void verification() throws IOException {
        if (LoginVerification.getInstance().verifyAdmin(username.getText(), password.getText())) {
            String name = username.getText().substring(0, 1).toUpperCase() + username.getText().substring(1);
            anchor.getScene().getWindow().hide();
            loadSecondScene("src/main/resources/dashboard/dashboardAdmin.fxml", name);
        } else if (LoginVerification.getInstance().verifyPuntori(username.getText(), password.getText())) {
            String name = username.getText().substring(0, 1).toUpperCase() + username.getText().substring(1);
            anchor.getScene().getWindow().hide();
            loadSecondScene("src/main/resources/dashboard/dashboardPuntori.fxml", name);
        }
        maintainLogin();
    }

    public void loadSecondScene(String path, String name) throws IOException {
        URL url = new File(path).toURI().toURL();
        Parent parent = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setTitle("Bilanci - " + name);
        stage.setScene(new Scene(parent, 955, 585));
        stage.setResizable(false);
        stage.show();
    }

    private void maintainLogin() {
        incorrect.setVisible(true);
        if (password.getText().trim().length() == 0 && username.getText().trim().length() == 0) {
            incorrect.setText("Enter information to login");
        } else if (username.getText().trim().length() == 0) {
            incorrect.setText("Enter username to login");
        } else if (password.getText().trim().length() == 0) {
            incorrect.setText("Enter password to login");
        } else {
            incorrect.setText("Username or Password you’ve entered is incorrect");
            password.clear();
        }
    }
}
