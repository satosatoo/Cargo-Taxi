package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RequestsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private DatePicker specificDate;

    @FXML
    void averageCost(ActionEvent event) {

    }

    @FXML
    void heaviestTransportation(ActionEvent event) {

    }

    @FXML
    void numOfCargoForSpecificDate(ActionEvent event) {

    }

    @FXML
    void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MenuController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specificDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Запрещает выбор дат, предшествующих сегодняшней дате
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
    }
}