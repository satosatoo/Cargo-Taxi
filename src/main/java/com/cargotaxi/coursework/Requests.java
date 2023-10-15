package com.cargotaxi.coursework;

import javafx.scene.control.Alert;

public class Requests {
    public static void errorNothingEntered() {
        // Display an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Info");
        alert.setHeaderText(null); // You can set a custom header text if needed
        alert.setContentText("Fill in at least one line.");

        alert.showAndWait(); // Show the dialog and wait for the user to close it
    }
}