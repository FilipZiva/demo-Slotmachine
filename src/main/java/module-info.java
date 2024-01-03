module com.example.demoslotmachine {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.slotmachine to javafx.fxml;
    exports com.example.slotmachine;
    exports com.example.slotmachine.Controller;
    opens com.example.slotmachine.Controller to javafx.fxml;
}