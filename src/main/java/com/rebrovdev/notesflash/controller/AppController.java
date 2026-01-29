package com.rebrovdev.notesflash.controller;

import com.rebrovdev.notesflash.model.canvas.CanvasRender;
import com.rebrovdev.notesflash.model.canvas.CanvasState;
import com.rebrovdev.notesflash.model.canvas.PageBackgroundType;
import com.rebrovdev.notesflash.model.tool.EraserTool;
import com.rebrovdev.notesflash.model.tool.Tool;
import com.rebrovdev.notesflash.model.tool.ToolFactory;
import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Optional;

public class AppController {

    @FXML
    private StackPane canvasContainer;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private Canvas backgroundCanvas;

    private GraphicsContext gc;
    private CanvasState canvasState;
//    private final Tool eraserTool = new EraserTool();
    private Tool currentTool;
    private PageBackgroundType currentGrid = PageBackgroundType.GRID;


    @FXML
    public void initialize() {
        canvasState = new CanvasState();
        gc = drawingCanvas.getGraphicsContext2D();
        currentTool = ToolFactory.createPen(drawingCanvas, canvasState);
        bgSettings(currentGrid);
        onMouseActions();
    }

    private void onMouseActions() {
        drawingCanvas.setOnMousePressed(e -> {
            currentTool.onPress(e.getX(), e.getY());
//            CanvasRender.redraw(gc, new CanvasState(), new Smoothing());
        });

        drawingCanvas.setOnMouseDragged(e -> {
            currentTool.onDrag(e.getX(), e.getY());
            CanvasRender.redraw(gc, canvasState);
//            CanvasRender.redraw(gc, new CanvasState(), new Smoothing());
        });

        drawingCanvas.setOnMouseReleased(e -> {
            currentTool.onRelease(e.getX(), e.getY());
//            CanvasRender.redraw(gc, new CanvasState(), new Smoothing());
        });
    }

    private void bgSettings(PageBackgroundType currentGrid) {
        backgroundCanvas.widthProperty().bind(canvasContainer.widthProperty());
        backgroundCanvas.heightProperty().bind(canvasContainer.heightProperty());

        drawingCanvas.widthProperty().bind(canvasContainer.widthProperty());
        drawingCanvas.heightProperty().bind(canvasContainer.heightProperty());

        canvasContainer.widthProperty().addListener((obs, o, n) -> drawPage(currentGrid));
        canvasContainer.heightProperty().addListener((obs, o, n) -> drawPage(currentGrid));
    }

    @FXML
    private void selectPen() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        currentTool = ToolFactory.createPen(drawingCanvas, canvasState);
    }

    @FXML
    private void selectEraser() {
        // TODO: Delete points, not "repaint" with white color
//        gc.setStroke(Color.WHITE);
//        gc.setLineWidth(20);
        currentTool = ToolFactory.createEraser(drawingCanvas, canvasState);
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

    @FXML
    private void changeBgGrid() {
        currentGrid = PageBackgroundType.GRID;
        bgSettings(currentGrid);
    }

    @FXML
    private void changeBgLines() {
        currentGrid = PageBackgroundType.LINES;
        canvasContainer.widthProperty().addListener((obs, o, n) -> drawPage(currentGrid));
        canvasContainer.heightProperty().addListener((obs, o, n) -> drawPage(currentGrid));
    }

    @FXML
    private void changeBgDots() {
        currentGrid = PageBackgroundType.DOTS;
        canvasContainer.widthProperty().addListener((obs, o, n) -> drawPage(currentGrid));
        canvasContainer.heightProperty().addListener((obs, o, n) -> drawPage(currentGrid));
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
