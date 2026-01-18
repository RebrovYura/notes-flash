module com.rebrovdev.notesflash {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.rebrovdev.notesflash to javafx.fxml;
    exports com.rebrovdev.notesflash;
}