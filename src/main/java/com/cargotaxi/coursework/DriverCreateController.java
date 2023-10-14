package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DriverCreateController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField fullName;

    @FXML
    private ChoiceBox<?> modelOfTruck;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField truckNumber;

    @FXML
    void saveToList(ActionEvent event) {

    }

    @FXML
    void switchToCargo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}