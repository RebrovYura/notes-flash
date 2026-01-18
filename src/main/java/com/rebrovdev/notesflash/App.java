package com.rebrovdev.notesflash;

import com.rebrovdev.notesflash.ui.CanvasView;
import com.rebrovdev.notesflash.viewmodel.CanvasViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        CanvasViewModel vm = new CanvasViewModel();
        CanvasView view = new CanvasView(vm);
        Scene scene = new Scene(view, 1000, 700);

        stage.setTitle("NotesFlash");
        stage.setScene(scene);
        stage.show();
    }
}
