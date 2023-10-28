package com.cargotaxi.coursework;

import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.util.Objects;

public class Requests {
    public static void averageCost(String selectedMonth, String selectedYear) throws DataNotFoundException {
        double sum = 0;
        int i = 0;
        double average = 0;
        for (Cargo cargo: Cargo.cargoList) {
            LocalDate cargoDate = Objects.requireNonNull(Contract.findContractById(cargo.getCargoId())).getDeliveryDate();
            if (cargoDate.getMonth().name().equalsIgnoreCase(selectedMonth) && cargoDate.getYear() == Integer.parseInt(selectedYear)) {
                sum += cargo.getPrice();
                i++;
            }
        }

        if (i == 0) {
            throw new DataNotFoundException("No data found for " + selectedMonth + " " + selectedYear);
        }

        average = sum / i;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("The average cost of the service for " + selectedMonth + " " + selectedYear + ": " + average);
        alert.showAndWait();
    }

    public static void heaviestTransportation(String selectedMonth, String selectedYear) throws DataNotFoundException {
        Cargo result = null;
        double heaviest = 0;
        for (Cargo cargo : Cargo.cargoList) {
            LocalDate cargoDate = Objects.requireNonNull(Contract.findContractById(cargo.getCargoId())).getDeliveryDate();
            if (cargoDate.getMonth().name().equalsIgnoreCase(selectedMonth) && cargoDate.getYear() == Integer.parseInt(selectedYear)) {
                if (cargo.getWeight() > heaviest) {
                    heaviest = cargo.getWeight();
                    result = cargo;
                }
            }
        }

        if (heaviest == 0) {
            throw new DataNotFoundException("No data found for the heaviest transportation in " + selectedMonth + " " + selectedYear);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("The heaviest transportation in " + selectedMonth + " " + selectedYear + ": " + heaviest + " kg.\n" +
                "Cargo name: " + result.getCargoName() + " | Cargo ID: " + result.getCargoId());
        alert.showAndWait();
    }

    public static void numOfCargoForSpecificDate(LocalDate specificDate) {
        int u = 0;
        LocalDate selectedDate = specificDate;
        for (Contract cont: Contract.contractList) {
            if (cont.getDeliveryDate().isEqual(selectedDate)) {
                u++;
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("The number of clients to whom the service is assigned for the specified period: " + u);
        alert.showAndWait();
    }
}