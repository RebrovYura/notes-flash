package com.rebrovdev.notesflash.ui;

import com.rebrovdev.notesflash.model.Stroke;
import com.rebrovdev.notesflash.render.PageRenderer;
import com.rebrovdev.notesflash.render.StrokeRenderer;
import com.rebrovdev.notesflash.tools.PenTool;
import com.rebrovdev.notesflash.tools.Tool;
import com.rebrovdev.notesflash.viewmodel.CanvasViewModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class CanvasView extends Pane {

    private final Canvas canvas = new Canvas();
    private final PageRenderer pageRenderer = new PageRenderer();
    private final StrokeRenderer strokeRenderer = new StrokeRenderer();
    private final CanvasViewModel viewModel;

    public CanvasView(CanvasViewModel viewModel) {

        this.viewModel = viewModel;
        getChildren().add(canvas);

        canvas.setFocusTraversable(true);
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());

        canvas.setOnMousePressed(e -> {
            PenTool pen = (PenTool) viewModel.getActiveTool();
            pen.onPress(e.getX(), e.getY());
//            Tool tool = viewModel.getActiveTool();
//            tool.onPress(e.getX(), e.getY());
            redraw();
        });
        canvas.setOnMouseDragged(e -> {
            PenTool pen = (PenTool) viewModel.getActiveTool();
            pen.onDrag(e.getX(), e.getY());
//            Tool tool = viewModel.getActiveTool();
//            tool.onDrag(e.getX(), e.getY());
            redraw();
        });
        canvas.setOnMouseReleased(e -> {
//            Tool tool = viewModel.getActiveTool();
            PenTool pen = (PenTool) viewModel.getActiveTool();
            pen.onRelease(e.getX(), e.getY());
            Stroke finished = pen.finishStroke();
            viewModel.addStroke(finished);
            redraw();
//            if (tool instanceof PenTool pen) {
//                pen.onRelease(e.getX(), e.getY());
//                viewModel.addStroke(pen.finishStroke());
//                redraw();
//            }
//            tool.onRelease(e.getX(), e.getY());

        });

        redraw();

    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        pageRenderer.render(gc, viewModel.getCurrentPage());

        PenTool pen = (PenTool) viewModel.getActiveTool();
        if (pen.hasPreview()) {
            strokeRenderer.render(gc, pen.getPreview());
        }
    }
}
