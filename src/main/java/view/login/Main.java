package view.login;

import datasource.Base;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/resources/login/login.fxml").toURI().toURL();
        Parent parent = FXMLLoader.load(url);
        stage.setTitle("Bilanci Login");
        stage.setScene(new Scene(parent,347.0,344.0));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!Base.getInstance().open()) {
            System.out.println("Error connecting to database");
            Platform.exit();
        } else {
            Base.getInstance().open();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Base.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
