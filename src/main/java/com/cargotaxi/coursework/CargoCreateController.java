package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class CargoCreateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private DatePicker appointment;

    @FXML
    private Text deliveryDate;

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

        if (pickUp.getText().equals(dropOff.getText())) {
            Cargo.errorIdenticalPickUpAndDropOff();
            i = 0;
        }

        LocalDate selectedDate = appointment.getValue();
        if (selectedDate != null && !selectedDate.isBefore(LocalDate.now())) { i++; }
        else { Cargo.errorDate(); }

        if (i == 5) {
            Cargo cargo = new Cargo(cargoName.getText(), pickUp.getText(), dropOff.getText(), weight.getText(), priceText.getText(), selectedDate, delivery);
            Cargo.cargoList.add(cargo);
            cargo.saveCargo();

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
        priceText.setText(price + "");
    }

    @FXML
    void switchToCargo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CargoController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    LocalDate delivery;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointment.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // запрещает выбор дат, предшествующих сегодняшней дате
                setDisable(date.isBefore(LocalDate.now()));
            }
        });

        appointment.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isBefore(LocalDate.now())) {
                if (weight.getText().isEmpty()) {
                    Cargo.errorWeight();
                } else {
                    try {
                        double cargoWeight = Double.parseDouble(weight.getText());
                        delivery = Driver.deliveryTime(newValue, cargoWeight, Car.getLimit());
                        deliveryDate.setText(delivery.toString());
                    } catch (NumberFormatException e) {
                        Cargo.errorWeight();
                    }
                }
            }
        });
    }
}