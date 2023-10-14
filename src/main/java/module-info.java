module com.coursework.coursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cargotaxi.coursework to javafx.fxml;
    exports com.cargotaxi.coursework;
}