package com.cargotaxi.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToCargo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CargoController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToContract(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ContractController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToDriver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DriverController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToOrderTaker(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OrderTakerController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToRequests(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RequestsController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openImage(ActionEvent event) throws IOException {
        // Создайте новое окно (Stage) для отображения изображения
        Stage imageStage = new Stage();

        // Загрузите изображение (замените "imagePath" на путь к вашему изображению)
        Image image = new Image("C:\\Users\\yser\\IdeaProjects\\coursework\\all.png");

        // Создайте ImageView для отображения изображения
        ImageView imageView = new ImageView(image);

        // Создайте новый корневой элемент (Parent) для сцены с изображением
        Parent imageRoot = new StackPane(imageView);

        // Создайте новую сцену (Scene) с изображением
        Scene imageScene = new Scene(imageRoot);

        // Установите сцену в окно и отобразите его на весь экран
        imageStage.setScene(imageScene);
        imageStage.setFullScreen(true);

        // Показать окно с изображением
        imageStage.show();
    }
}