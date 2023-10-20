package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class DriverChangeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField carNumber;

    @FXML
    private TextField fullName;

    @FXML
    private ChoiceBox<String> modelOfCar;

    @FXML
    private TextField phoneNumber;

    @FXML
    void deleteDriver(ActionEvent event) throws IOException {
        // Создайте диалоговое окно Alert с запросом подтверждения
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this Driver?");

        // Определите кнопки "OK" и "Cancel" в диалоговом окне
        ButtonType okButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        confirmationAlert.getButtonTypes().setAll(okButton, cancelButton);

        // Отобразите диалоговое окно и дождитесь ответа пользователя
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            int driverId = selectedDriver.getId();
            Driver.deleteDriver(driverId);

            Driver.deleteDriverFromFile(driverId);

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverListController.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {
        if (Driver.isValidPhoneNumber(phoneNumber.getText()) && phoneNumber.getText() != null) {
            selectedDriver.setPhoneNumber(phoneNumber.getText());

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverListController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Driver.errorPhoneNumber();
        }
    }

    @FXML
    void switchToDriver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverListController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Driver selectedDriver;

    public void setDriver(Driver driver) {
        selectedDriver = driver;
        fullName.setText(driver.getFullName());
        fullName.setDisable(true); // Запретите редактирование значения
        modelOfCar.setValue(driver.car.getCarModel());
        modelOfCar.setDisable(true); // Запретите редактирование значения
        carNumber.setText(driver.car.getCarNumber());
        carNumber.setDisable(true); // Запретите редактирование значения
    }
}