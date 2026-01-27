module com.rebrovdev.notesflash {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports com.rebrovdev.notesflash.controller;
    exports com.rebrovdev.notesflash.model;
    opens com.rebrovdev.notesflash.controller to javafx.fxml;
    exports com.rebrovdev.notesflash.model.canvas;
    exports com.rebrovdev.notesflash.app;
    opens com.rebrovdev.notesflash.app to javafx.fxml;
}