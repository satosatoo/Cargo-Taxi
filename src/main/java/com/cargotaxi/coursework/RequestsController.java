package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class RequestsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ComboBox<String> selectMonth;

    @FXML
    private ComboBox<String> selectYear;

    @FXML
    private DatePicker specificDate;

    @FXML
    void averageCost(ActionEvent event) {
        String selectedMonth = selectMonth.getValue();
        String selectedYear = selectYear.getValue();
        try {
            Requests.averageCost(selectedMonth, selectedYear);
        } catch (DataNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void heaviestTransportation(ActionEvent event) {
        String selectedMonth = selectMonth.getValue();
        String selectedYear = selectYear.getValue();
        try {
            Requests.heaviestTransportation(selectedMonth, selectedYear);
        } catch (DataNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void numOfCargoForSpecificDate(ActionEvent event) {
        LocalDate sd = specificDate.getValue();
        Requests.numOfCargoForSpecificDate(sd);
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

        selectMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        selectYear.getItems().addAll("2023", "2024", "2025", "2026");
    }
}