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
        Stage imageStage = new Stage();

        Image image = new Image("C:\\Users\\yser\\IdeaProjects\\coursework\\all.png");

        ImageView imageView = new ImageView(image);

        Parent imageRoot = new StackPane(imageView);

        Scene imageScene = new Scene(imageRoot);

        imageStage.setScene(imageScene);
        imageStage.setFullScreen(true);

        imageStage.show();
    }
}