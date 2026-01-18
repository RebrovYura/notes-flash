package com.rebrovdev.notesflash.ui;

import com.rebrovdev.notesflash.viewmodel.CanvasViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class CanvasView extends Pane {
    private final Canvas canvas = new Canvas();

    public CanvasView(CanvasViewModel viewModel) {
        getChildren().add(canvas);

        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

    }
}
