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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DriverDetailsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ListView<Driver> listOfDrivers;

    @FXML
    void switchToDriver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RequestsController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Driver> driverObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Driver> driverObservableList = FXCollections.observableArrayList(Driver.driverList);
        listOfDrivers.setItems(driverObservableList);

        listOfDrivers.setCellFactory(param -> new ListCell<Driver>() {
            @Override
            protected void updateItem(Driver driver, boolean empty) {
                super.updateItem(driver, empty);
                if (empty || driver == null) {
                    setText(null);
                } else {
                    setText(driver.showInfo());
                }
            }
        });
    }
}