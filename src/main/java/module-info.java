module com.rebrovdev.notesflash {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.rebrovdev.notesflash to javafx.fxml;
    exports com.rebrovdev.notesflash;
    exports com.rebrovdev.notesflash.controller;
    exports com.rebrovdev.notesflash.model;
    opens com.rebrovdev.notesflash.controller to javafx.fxml;
}