package com.rebrovdev.notesflash.ui;

import com.rebrovdev.notesflash.render.PageRenderer;
import com.rebrovdev.notesflash.render.StrokeRenderer;
import com.rebrovdev.notesflash.tools.Tool;
import com.rebrovdev.notesflash.viewmodel.CanvasViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class CanvasView extends Pane {

    private final Canvas canvas = new Canvas();
    // private final StrokeRenderer renderer = new StrokeRenderer();
    private final PageRenderer renderer = new PageRenderer();
    private final CanvasViewModel viewModel;

    public CanvasView(CanvasViewModel viewModel) {
        this.viewModel = viewModel;
        getChildren().add(canvas);

        canvas.setFocusTraversable(true);
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        // GraphicsContext gc = canvas.getGraphicsContext2D();

        // canvas.setOnMousePressed(e -> viewModel.onMousePresses(e.getX(), e.getY()));
        // canvas.setOnMouseDragged(e -> viewModel.onMouseDragged(e.getX(), e.getY()));
        // canvas.setOnMouseReleased(e -> viewModel.onMouseReleased(e.getX(),
        // e.getY()));

        // gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // renderer.render(gc, viewModel);
        // canvas.setOnMousePressed(e -> viewModel.getActiveTool().onPress(e.getX(),
        // e.getY()));
        // canvas.setOnMouseDragged(e -> viewModel.getActiveTool().onDrag(e.getX(),
        // e.getY()));
        canvas.setOnMousePressed(e -> {
            Tool tool = viewModel.getActiveTool();
            System.out.println("calling tool.Pressed");
            tool.onPress(e.getX(), e.getY());
            redraw();
        });
        canvas.setOnMouseDragged(e -> {
            Tool tool = viewModel.getActiveTool();
            System.out.println("calling tool.Dragged");
            tool.onDrag(e.getX(), e.getY());
            redraw();
        });
        canvas.setOnMouseReleased(e -> {
            Tool tool = viewModel.getActiveTool();
            System.out.println("calling tool.onRelease");
            tool.onRelease(e.getX(), e.getY());
            redraw();
        });

//        viewModel.currentPageProperty().addListener((obs, oldPage, newPage) -> redraw());

        redraw();

    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        System.out.println("redraw");
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        renderer.render(gc, viewModel.getCurrentPage());
    }
}
