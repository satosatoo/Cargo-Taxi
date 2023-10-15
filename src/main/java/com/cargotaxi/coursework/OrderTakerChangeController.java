package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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
    void deleteOrderTaker(ActionEvent event) throws IOException {

        // Создайте диалоговое окно Alert с запросом подтверждения
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this OrderTaker?");

        // Определите кнопки "OK" и "Cancel" в диалоговом окне
        ButtonType okButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        confirmationAlert.getButtonTypes().setAll(okButton, cancelButton);

        // Отобразите диалоговое окно и дождитесь ответа пользователя
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            int orderTakerId = selectedOrderTaker.getId();
            OrderTaker.deleteOrderTaker(orderTakerId);

            selectedOrderTaker.setOfficeAddress(officeAddress.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerListController.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {
        if (OrderTaker.isValidPhoneNumber(phoneNumber.getText()) && phoneNumber.getText() != null &&
                OrderTaker.isValidAddress(officeAddress.getText()) && officeAddress.getText() != null) {
            selectedOrderTaker.setPhoneNumber(phoneNumber.getText());

            selectedOrderTaker.setOfficeAddress(officeAddress.getText());
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerListController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (OrderTaker.isValidPhoneNumber(phoneNumber.getText()) && phoneNumber.getText() != null) {
            selectedOrderTaker.setPhoneNumber(phoneNumber.getText());

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerListController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (OrderTaker.isValidAddress(officeAddress.getText()) && officeAddress.getText() != null) {
            selectedOrderTaker.setOfficeAddress(officeAddress.getText());

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerListController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            OrderTaker.errorNothingEntered();
        }
    }

    @FXML
    void switchToOrderTaker(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerListController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private OrderTaker selectedOrderTaker;

    public void setOrderTaker(OrderTaker orderTaker) {
        selectedOrderTaker = orderTaker;
        fullName.setText(orderTaker.getFullName());
        fullName.setDisable(true); // Запретите редактирование значения
    }
}