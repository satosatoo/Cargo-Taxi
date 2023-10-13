module com.coursework.coursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.coursework.coursework to javafx.fxml;
    exports com.coursework.coursework;
}