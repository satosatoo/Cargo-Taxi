package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DriverListController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ListView<?> listOfDrivers;

    @FXML
    void switchToDriverChange(ActionEvent event) throws IOException {


        // сделать выбор определенного водителя


        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverChangeController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToDriver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DriverController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // here must be List and in initialize() add this List

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}