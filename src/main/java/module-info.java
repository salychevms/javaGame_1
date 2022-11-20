module com.example.javagame_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javagame_1 to javafx.fxml;
    exports com.example.javagame_1;
}