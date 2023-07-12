module com.example.lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.lab4 to javafx.fxml;
    exports com.example.lab4;
}