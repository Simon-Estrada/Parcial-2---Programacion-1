module com.example.inmobiliaria {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.inmobiliaria to javafx.fxml;
    exports com.example.inmobiliaria;
    exports Controllers;
    opens Controllers to javafx.fxml;
}