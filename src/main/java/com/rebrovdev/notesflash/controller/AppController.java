package com.rebrovdev.notesflash.controller;

import com.rebrovdev.notesflash.model.PageBackgroundType;
import com.rebrovdev.notesflash.tools.EraserTool;
import com.rebrovdev.notesflash.tools.PenTool;
import com.rebrovdev.notesflash.tools.Tool;
import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

import java.util.Optional;

public class AppController {
    @FXML
    private StackPane canvasContainer;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private Canvas backgroundCanvas;

    private GraphicsContext gc;
    private Smoothing smoothing;

//    private final Tool penTool = new PenTool(gc);
    private final Tool eraserTool = new EraserTool();

//    private Tool currentTool = penTool;


    @FXML
    public void initialize() {
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
        Tool penTool = new PenTool(drawingCanvas.getGraphicsContext2D(), smoothing);

        backgroundCanvas.widthProperty().bind(canvasContainer.widthProperty());
        backgroundCanvas.heightProperty().bind(canvasContainer.heightProperty());

        drawingCanvas.widthProperty().bind(canvasContainer.widthProperty());
        drawingCanvas.heightProperty().bind(canvasContainer.heightProperty());

        canvasContainer.widthProperty().addListener((obs, o, n) -> drawPage(PageBackgroundType.DOTS));
        canvasContainer.heightProperty().addListener((obs, o, n) -> drawPage(PageBackgroundType.DOTS));

//        selectPen();

        drawingCanvas.setOnMousePressed(e -> {
            penTool.onPress(e.getX(), e.getY());
            System.out.println(penTool);
            System.out.println("Controller onPress: " + e.getX() + "   " + e.getY());
        });

        drawingCanvas.setOnMouseDragged(e -> {
            penTool.onDrag(e.getX(), e.getY());
        });

        drawingCanvas.setOnMouseReleased(e -> {
            penTool.onRelease(e.getX(), e.getY());
        });
    }

    @FXML
    private void selectPen() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
//        currentTool = penTool;
    }

    @FXML
    private void selectEraser() {
        // TODO: Delete points, not "repaint" with white color
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(20);
//        currentTool = eraserTool;
    }

    @FXML
    private void clearCanvas() {
        Alert alertWindow = new Alert(Alert.AlertType.CONFIRMATION);
        alertWindow.setTitle("Внимание!");
        alertWindow.setHeaderText("Очистить холст?");
        alertWindow.setContentText("Все несохраненные изменения будут удалены.");

        Optional<ButtonType> result = alertWindow.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        }
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

    private void dotsPage() {
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, backgroundCanvas.getWidth(), backgroundCanvas.getHeight());

        gc.setFill(Color.LIGHTGRAY);

        double spacing = 35;
        double dotRadius = 1.5;

        for (double i = 1.5; i < backgroundCanvas.getWidth(); i += spacing) {
            for (double j = 1.5; j < backgroundCanvas.getHeight(); j += spacing) {
                gc.fillOval(i - dotRadius, j - dotRadius, dotRadius * 2, dotRadius * 2);
            }
        }
    }

    public void drawPage(PageBackgroundType pageType) {
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
        double w = backgroundCanvas.getWidth();
        double h = backgroundCanvas.getHeight();

        gc.clearRect(0, 0, w, h);

        switch (pageType) {
            case DOTS -> dotsPage();
            case GRID -> gridPage();
            case LINES -> linesPage();
            default -> {}
        }
    }

}
