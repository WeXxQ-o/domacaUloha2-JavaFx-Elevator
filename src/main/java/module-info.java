module com.example.vytah {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vytah to javafx.fxml;
    exports com.example.vytah;
}