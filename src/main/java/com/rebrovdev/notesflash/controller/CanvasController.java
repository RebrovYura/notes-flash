package com.rebrovdev.notesflash.controller;

import com.rebrovdev.notesflash.model.PageBackgroundType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasController {

    @FXML
    private Canvas backgroundCanvas;

    private GraphicsContext gc;

    public void dotsPage(){

    }

    private void linesPage() {
        gc = backgroundCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, backgroundCanvas.getWidth(), backgroundCanvas.getHeight());

        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);

        double spacing = 20;

        for (double i = spacing; i < backgroundCanvas.getHeight(); i += spacing) {
            gc.strokeLine(0, i, backgroundCanvas.getWidth(), i);
        }
    }

    private void gridPage() {

    }

    private void blankPage() {

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