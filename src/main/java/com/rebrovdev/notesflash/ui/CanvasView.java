package com.rebrovdev.notesflash.ui;

import com.rebrovdev.notesflash.render.StrokeRenderer;
import com.rebrovdev.notesflash.viewmodel.CanvasViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class CanvasView extends Pane {

    private final Canvas canvas = new Canvas();
    private final StrokeRenderer renderer = new StrokeRenderer();

    public CanvasView(CanvasViewModel viewModel) {
        getChildren().add(canvas);

        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> viewModel.onMousePresses(e.getX(), e.getY()));
        canvas.setOnMouseDragged(e -> viewModel.onMouseDragged(e.getX(), e.getY()));
        canvas.setOnMouseReleased(e -> viewModel.onMouseReleased(e.getX(), e.getY()));

        viewModel.currentPageProperty().addListener((obs, old, page) -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            page.getStrokes().forEach(s -> renderer.render(gc, s));
        });
    }
}
