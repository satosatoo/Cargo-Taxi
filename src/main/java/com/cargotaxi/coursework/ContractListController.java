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

public class ContractListController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView<Contract> listOfContracts;

    @FXML
    void switchToContract(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ContractController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Contract> contractObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the observable list with your orderTakerList
        contractObservableList = FXCollections.observableArrayList(Contract.contractList);
        listOfContracts.setItems(contractObservableList);

        // Установите фабрику ячеек (cell factory) для отображения информации о каждом объекте в списке
        listOfContracts.setCellFactory(param -> new ListCell<Contract>() {
            @Override
            protected void updateItem(Contract contract, boolean empty) {
                super.updateItem(contract, empty);
                if (empty || contract == null) {
                    setText(null);
                } else {
                    // В этой строке можно настроить формат отображения информации
                    setText(contract.showInfo());
                }
            }
        });
    }
}