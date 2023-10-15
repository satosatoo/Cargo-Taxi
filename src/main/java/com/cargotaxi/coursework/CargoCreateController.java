package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
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
    private Text priceText;

    @FXML
    private TextField dropOff;

    @FXML
    private TextField pickUp;

    @FXML
    private TextField weight;

    @FXML
    void saveToList(ActionEvent event) throws IOException {
        int i = 0;
        if (Cargo.isValidCargoName(cargoName.getText())) { i++; }
        else { Cargo.errorCargoName(); }
        if (Cargo.isValidPickupAndDropOffLocation(pickUp.getText())) { i++; }
        else { Cargo.errorPickUp(); }
        if (Cargo.isValidPickupAndDropOffLocation(dropOff.getText())) { i++; }
        else { Cargo.errorDropOff(); }
        if (Cargo.isValidWeight(weight.getText())) { i++; }
        else { Cargo.errorWeight(); }
        if (i == 4) {
            Cargo cargo = new Cargo(cargoName.getText(), pickUp.getText(), dropOff.getText(), weight.getText(), priceText.getText());
            Cargo.cargoList.add(cargo);

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CargoController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else { i = 0; }
    }

    @FXML
    void updatePrice(KeyEvent event) {
        String str = weight.getText();
        double price = Double.parseDouble(str) * Cargo.getPricePerKg();
        priceText.setText("" + price);
    }

    @FXML
    void switchToCargo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CargoController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}