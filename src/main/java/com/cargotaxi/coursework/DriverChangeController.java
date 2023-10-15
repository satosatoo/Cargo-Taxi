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

public class DriverChangeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField carNumber;

    @FXML
    private TextField fullName;

    @FXML
    private ChoiceBox<?> modelOfCar;

    @FXML
    private TextField phoneNumber;

    @FXML
    void deleteDriver(ActionEvent event) {
                                                // init
    }

    @FXML
    void saveChanges(ActionEvent event) {
                                                // init
    }

    @FXML
    void switchToDriver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverListController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}