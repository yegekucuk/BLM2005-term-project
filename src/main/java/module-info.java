module com.example.donemprojesi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.donemprojesi to javafx.fxml;
    exports com.example.donemprojesi;
}