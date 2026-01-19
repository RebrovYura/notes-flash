module com.rebrovdev.notesflash {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.rebrovdev.notesflash to javafx.fxml;
    exports com.rebrovdev.notesflash;
}