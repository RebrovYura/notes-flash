package com.rebrovdev.notesflash.controller;

import com.rebrovdev.notesflash.model.PageBackgroundType;
import com.rebrovdev.notesflash.tools.EraserTool;
import com.rebrovdev.notesflash.tools.PenTool;
import com.rebrovdev.notesflash.tools.Tool;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class AppController {

    private final Tool penTool = new PenTool();
    private final Tool eraserTool = new EraserTool();
    private Tool currentTool = penTool;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private Canvas backgroundCanvas;

    @FXML
    private StackPane canvasContainer;

    private GraphicsContext gc;

    @FXML
    public void initialize() {
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);

        backgroundCanvas.widthProperty().bind(canvasContainer.widthProperty());
        backgroundCanvas.heightProperty().bind(canvasContainer.heightProperty());

        drawingCanvas.widthProperty().bind(canvasContainer.widthProperty());
        drawingCanvas.heightProperty().bind(canvasContainer.heightProperty());

        canvasContainer.widthProperty().addListener((obs, o, n) -> drawPage(PageBackgroundType.GRID));
        canvasContainer.heightProperty().addListener((obs, o, n) -> drawPage(PageBackgroundType.GRID));

        selectPen();

        drawingCanvas.setOnMousePressed(e -> {
            currentTool.onPress(gc, e.getX(), e.getY());
            System.out.println(currentTool);
            System.out.println("Controller onPress: " + e.getX() + "   " + e.getY());
        });

        drawingCanvas.setOnMouseDragged(e -> {
            currentTool.onDrag(gc, e.getX(), e.getY());
        });

        drawingCanvas.setOnMouseReleased(e -> {
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
        // TODO: Delete points, not "repaint" with white color
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(20);
        currentTool = eraserTool;
    }

    @FXML
    private void clearCanvas() {
        gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }

    private void linesPage() {
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, backgroundCanvas.getWidth(), backgroundCanvas.getHeight());

        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);

        double spacing = 35;

        for (double i = spacing; i < backgroundCanvas.getHeight(); i += spacing) {
            gc.strokeLine(0, i, backgroundCanvas.getWidth(), i);
        }
    }

    private void gridPage() {
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, backgroundCanvas.getWidth(), backgroundCanvas.getHeight());

        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);

        double spacing = 35;

        for (double i = 0; i < backgroundCanvas.getWidth(); i += spacing) {
            gc.strokeLine(0, i, backgroundCanvas.getWidth(), i);
        }
        for (double j = 0; j < backgroundCanvas.getWidth(); j += spacing) {
            gc.strokeLine(j, 0, j, backgroundCanvas.getHeight());
        }
    }

    public void drawPage(PageBackgroundType pageType) {
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
        double w = backgroundCanvas.getWidth();
        double h = backgroundCanvas.getHeight();

        gc.clearRect(0, 0, w, h);

        switch (pageType) {
//            case DOTS -> dotsPage();
            case GRID -> gridPage();
            case LINES -> linesPage();
            default -> {}
        }

    }


}
