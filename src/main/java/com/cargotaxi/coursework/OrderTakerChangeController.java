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

public class OrderTakerChangeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField fullName;       // show name but cannot edit

    @FXML
    private TextField officeAddress;

    @FXML
    private TextField phoneNumber;

    @FXML
    void deleteOrderTaker(ActionEvent event) {
                                                    // realization
    }

    @FXML
    void saveChanges(ActionEvent event) {
                                                    // realizations
    }

    @FXML
    void switchToOrderTaker(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}