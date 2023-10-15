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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class OrderTakerListController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView<OrderTaker> listOfOrderTakers;

    @FXML
    void changeOrderTaker(ActionEvent event) throws IOException {
        OrderTaker selectedOrderTaker = listOfOrderTakers.getSelectionModel().getSelectedItem();
        if (selectedOrderTaker != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderTakerChangeController.fxml"));
                Parent changeRoot = loader.load();
                OrderTakerChangeController changeController = loader.getController();
                changeController.setOrderTaker(selectedOrderTaker); // Передайте выбранный объект OrderTaker в следующий контроллер
                Scene changeScene = new Scene(changeRoot);
                Stage changeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                changeStage.setScene(changeScene);
                changeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void switchToOrderTaker(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderTakerController.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<OrderTaker> orderTakerObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the observable list with your orderTakerList
        orderTakerObservableList = FXCollections.observableArrayList(OrderTaker.orderTakerList);
        listOfOrderTakers.setItems(orderTakerObservableList);

        // Установите фабрику ячеек (cell factory) для отображения информации о каждом объекте в списке
        listOfOrderTakers.setCellFactory(param -> new ListCell<OrderTaker>() {
            @Override
            protected void updateItem(OrderTaker orderTaker, boolean empty) {
                super.updateItem(orderTaker, empty);
                if (empty || orderTaker == null) {
                    setText(null);
                } else {
                    // В этой строке можно настроить формат отображения информации
                    setText(orderTaker.showInfo());
                }
            }
        });
    }
}