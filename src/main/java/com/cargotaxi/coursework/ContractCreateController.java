package com.cargotaxi.coursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ContractCreateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<Cargo> cargo;

    @FXML
    private ChoiceBox<Driver> driver;

    @FXML
    private ChoiceBox<OrderTaker> orderTaker;

    @FXML
    void saveToList(ActionEvent event) throws IOException {
        int i = 0;
        // Check if any of the ChoiceBox selections are null
        if (cargo.getValue() != null) { i++; }
        else { Contract.errorCargo(); }
        if (orderTaker.getValue() != null) { i++; }
        else { Contract.errorOrderTaker(); }
        if (driver.getValue() != null) { i++; }
        else { Contract.errorDriver(); }

        if (i == 3) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Create contract confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to create this contract?");

            ButtonType okButton = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(okButton, cancelButton);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == okButton) { i++; }
        }

        if (i == 4) {
            Contract contract = new Contract(cargo.getValue(), orderTaker.getValue(), driver.getValue());
            contract.cargoC.setCargoStatus(false);
            Cargo.cargoList.get(contract.cargoC.getCargoId()).setCargoStatus(false);
            contract.driverC.setDriverStatus(false);
            Driver.driverList.get(contract.driverC.getId()).setDriverStatus(false);
            Contract.contractList.add(contract);
            contract.saveContract();

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ContractController.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else { i = 0; }
    }

    @FXML
    void switchToContract(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ContractController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Create ObservableLists for each class
        ObservableList<OrderTaker> orderTakerList = FXCollections.observableArrayList(OrderTaker.orderTakerList);
        ObservableList<Cargo> cargoList = FXCollections.observableArrayList(
                Cargo.cargoList
                        .stream()
                        .filter(Cargo::getCargoStatusBoolean)
                        .collect(Collectors.toList())
        );

        ObservableList<Driver> driverList = FXCollections.observableArrayList(
                Driver.driverList
                        .stream()
                        .filter(Driver::getDriverStatusBoolean)
                        .collect(Collectors.toList())
        );

        // Set the items for each ChoiceBox
        cargo.setItems(cargoList);
        orderTaker.setItems(orderTakerList);
        driver.setItems(driverList);

        // Customize the display of items in the ChoiceBoxes using a StringConverter
        orderTaker.setConverter(new StringConverter<OrderTaker>() {
            @Override
            public String toString(OrderTaker object) {
                if (object == null) return null;
                return "OrderTaker ID: " + object.getId() + "  -  " + object.getFullName();
            }

            @Override
            public OrderTaker fromString(String string) {
                return null;
            }
        });

        cargo.setConverter(new StringConverter<Cargo>() {
            @Override
            public String toString(Cargo object) {
                if (object == null) return null;
                return "Cargo ID: " + object.getCargoId() + "  -  " + object.getCargoName();
            }

            @Override
            public Cargo fromString(String string) {
                return null;
            }
        });

        driver.setConverter(new StringConverter<Driver>() {
            @Override
            public String toString(Driver object) {
                if (object == null) return null;
                return "Driver ID: " + object.getId() + "  -  " + object.getFullName();
            }

            @Override
            public Driver fromString(String string) {
                return null;
            }
        });

    }
}