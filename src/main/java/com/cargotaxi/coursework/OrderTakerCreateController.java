package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class OrderTakerCreateController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField fullName;

    @FXML
    private TextField officeAddress;

    @FXML
    private TextField phoneNumber;

    @FXML
    void saveToList(ActionEvent event) throws IOException {
        int i = 0;
        if (OrderTaker.isValidName(fullName.getText())) {
            if (!OrderTaker.isNameAlreadyExists(fullName.getText())) {
                i++;
            } else {
                OrderTaker.errorNameExists();
            }
        }
        else { OrderTaker.errorName(); }
        if (OrderTaker.isValidPhoneNumber(phoneNumber.getText())) {
            if (!Driver.isPhoneNumberAlreadyExists(phoneNumber.getText())) {
                i++;
            } else {
                Driver.errorPhoneNumberExists();
            }
        }
        else { OrderTaker.errorPhoneNumber(); }
        if (OrderTaker.isValidAddress(officeAddress.getText())) { i++; }
        else { OrderTaker.errorOfficeAddress(); }
        if (i == 3) {
            // Create a new OrderTaker object
            OrderTaker orderTaker = new OrderTaker(fullName.getText(), phoneNumber.getText(), officeAddress.getText());
            OrderTaker.orderTakerList.add(orderTaker);
            // Save the new OrderTaker to the text file
            orderTaker.saveOrderTaker();

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else { i = 0; }
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