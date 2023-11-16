package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DriverCreateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ChoiceBox<String> carModel;

    @FXML
    private TextField carNumber;

    @FXML
    private TextField fullName;

    @FXML
    private TextField phoneNumber;

    @FXML
    void saveToList(ActionEvent event) throws IOException {
        int i = 0;
        if (Driver.isValidName(fullName.getText())) {
            if (!Driver.isNameAlreadyExists(fullName.getText())) {
                i++;
            } else {
                Driver.errorNameExists();
            }
        }
        else { Driver.errorName(); }
        if (Driver.isValidPhoneNumber(phoneNumber.getText())) {
            if (!Driver.isPhoneNumberAlreadyExists(phoneNumber.getText())) {
                i++;
            } else {
                Driver.errorPhoneNumberExists();
            }
        }
        else { Driver.errorPhoneNumber(); }
        if (carModel.getValue() != null) { i++; }
        else { Driver.errorChooseCarModel(); }

        if (i == 3) {
            Driver driver = new Driver(fullName.getText(), phoneNumber.getText(), carModel.getValue(), carNumber.getText());
            Driver.driverList.add(driver);
            driver.saveDriver();

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else { i = 0; }
    }

    @FXML
    void switchToCargo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carNumber.setText(Car.createUniqueCarNumber());
        carModel.getItems().addAll("Nissan", "Mercedes", "Suzuki", "УАЗ");
    }
}