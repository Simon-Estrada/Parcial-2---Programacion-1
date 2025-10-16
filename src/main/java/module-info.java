module com.example.inmobiliaria {
    requires javafx.controls;
    requires javafx.fxml;


    opens Controllers to javafx.fxml;
    opens Objects to javafx.base;


    exports Controllers;
    exports Objects;
    exports com.example.inmobiliaria;
}