package com.cargotaxi.coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Objects;

public class Menu extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuController.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            OrderTaker.readOrderTaker();
            Driver.readDriver();
            Cargo.readCargo();
            Contract.readContract();
        } catch(Exception e) {
            e.printStackTrace();
        }

        stage.setOnCloseRequest(event -> onClose());
    }

    private void onClose() {
        Cargo.rewriteCargoFile();
        Driver.rewriteDriverFile();
        OrderTaker.rewriteOrderTakerFile();
        Contract.rewriteContractFile();
    }

    public static void main(String[] args) {
        launch(args);
    }
}