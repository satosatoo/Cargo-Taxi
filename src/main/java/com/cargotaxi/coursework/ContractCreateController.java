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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ContractCreateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Text deliveryDate;

    @FXML
    private ChoiceBox<Cargo> cargo;

    @FXML
    private DatePicker appointment;

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
        if (orderTaker.getValue() != null) {i++; }
        else { Contract.errorOrderTaker(); }
        if (driver.getValue() != null) {i++; }
        else { Contract.errorDriver(); }

        LocalDate selectedDate = appointment.getValue();
        if (selectedDate != null && !selectedDate.isBefore(LocalDate.now())) { i++; }
        else { Contract.errorDate(); }

        if (i == 4) {
            Contract contract = new Contract(cargo.getValue(), orderTaker.getValue(), driver.getValue(), selectedDate, delivery);
            contract.cargoC.setCargoStatusOnTheWay();
            contract.driverC.setDriverStatusBusy();
            Contract.contractList.add(contract);

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

    LocalDate delivery;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointment.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Запрещает выбор дат, предшествующих сегодняшней дате
                setDisable(date.isBefore(LocalDate.now()));
            }
        });

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

        // Добавьте слушатель событий к DatePicker
        appointment.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isBefore(LocalDate.now())) {
                // Получите соответствующую дату доставки
                delivery = Driver.deliveryTime(newValue, cargo.getValue().getWeight(), Driver.car.getLimit());
                deliveryDate.setText(delivery.toString());
            }
        });
    }
}