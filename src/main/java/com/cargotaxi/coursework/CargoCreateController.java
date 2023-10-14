package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CargoCreateController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField cargoName;

    @FXML
    private TextField dropOff;

    @FXML
    private TextField pickUp;

    @FXML
    private TextField weight;

    @FXML
    void saveToList(ActionEvent event) {
                                                // realization
    }

    @FXML
    void switchToCargo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}