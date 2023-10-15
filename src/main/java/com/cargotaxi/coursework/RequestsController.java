package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        double sum = 0;
        int i = 0;
        double average = 0;
        for (Cargo cargo: Cargo.cargoList) {
            sum += cargo.getPrice();
            i++;
        }
        average = sum / i;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("The average cost of the service: " + average);
        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    @FXML
    void heaviestTransportation(ActionEvent event) {
        Cargo result = null;
        double heaviest = Cargo.cargoList.get(0).getWeight();
        for (int i = 1; i < Cargo.cargoList.size(); i++) {
            if (heaviest < Cargo.cargoList.get(i).getWeight()) {
                heaviest = Cargo.cargoList.get(i).getWeight();
                result = Cargo.cargoList.get(i);
            }
        }

        // Display an error message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("The heaviest transportation: " + heaviest + "\n" +
                "Cargo name: " + result.getCargoName() + " | Cargo ID: " + result.getCargoId());
        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }

    @FXML
    void numOfCargoForSpecificDate(ActionEvent event) {
        int u = 0;
        LocalDate selectedDate = specificDate.getValue();
        for (Contract cont: Contract.contractList) {
            if (cont.getDeliveryDate().isEqual(selectedDate)) {
                u++;
            }
        }
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("The number of clients to whom the service is assigned for the specified period: " + u);
        alert.showAndWait(); // Show the dialog and wait for the user to close it
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