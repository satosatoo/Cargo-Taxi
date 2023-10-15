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

public class CargoListController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView<Cargo> listOfCargoes;

    @FXML
    void switchToCargo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CargoController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Cargo> cargoObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the observable list with your orderTakerList
        cargoObservableList = FXCollections.observableArrayList(Cargo.cargoList);
        listOfCargoes.setItems(cargoObservableList);

        // Установите фабрику ячеек (cell factory) для отображения информации о каждом объекте в списке
        listOfCargoes.setCellFactory(param -> new ListCell<Cargo>() {
            @Override
            protected void updateItem(Cargo cargo, boolean empty) {
                super.updateItem(cargo, empty);
                if (empty || cargo == null) {
                    setText(null);
                } else {
                    // В этой строке можно настроить формат отображения информации
                    setText(cargo.showInfo());
                }
            }
        });
    }
}