package com.rebrovdev.notesflash.controller;

import com.rebrovdev.notesflash.tools.EraserTool;
import com.rebrovdev.notesflash.tools.PenTool;
import com.rebrovdev.notesflash.tools.Tool;
import com.rebrovdev.notesflash.tools.ToolType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class AppController {

    private final Tool penTool = new PenTool();
    private final Tool eraserTool = new EraserTool();
//    private final Tool markerTool = new markerTool();

    private Tool currentTool = penTool;

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
//    private ToolType currentTool = ToolType.PEN;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);

        canvas.setOnMousePressed(e -> {
            currentTool.onPress(gc, e.getX(), e.getY());
            System.out.println(currentTool);
            System.out.println("Controller onPress: " + e.getX() + "   " + e.getY());
        });

        canvas.setOnMouseDragged(e -> {
            currentTool.onDrag(gc, e.getX(), e.getY());
        });

        canvas.setOnMouseReleased(e -> {
            currentTool.onRelease(gc, e.getX(), e.getY());
        });
    }

    @FXML
    private void selectPen() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        currentTool = penTool;
    }

    @FXML
    private void selectEraser() {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(5);
        currentTool = eraserTool;
    }

    @FXML
    private void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
