package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ContractCreateController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox<?> cargo;

    @FXML
    private DatePicker deliveryDate;

    @FXML
    private ChoiceBox<?> driver;

    @FXML
    private ChoiceBox<?> orderTaker;

    @FXML
    void saveToList(ActionEvent event) {
                                                // realization
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
        deliveryDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Запрещает выбор дат, предшествующих сегодняшней дате
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
    }
}