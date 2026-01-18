package com.rebrovdev.notesflash.tools;

import com.rebrovdev.notesflash.model.Stroke;

import java.util.function.Consumer;

public class PenTool implements Tool {

    private Stroke currentStroke;
    private final Consumer<Stroke> onStrokeFinished;
    private int color;
    private double width;

    public PenTool(int color, double width, Consumer<Stroke> onStrokeFinished) {
        this.color = color;
        this.width = width;
        this.onStrokeFinished = onStrokeFinished;
        this.currentStroke = null;
    }

    @Override
    public void onPress(double x, double y) {
        currentStroke = new Stroke(color, width);
        currentStroke.addPoint(x, y);
    }

    @Override
    public void onDrag(double x, double y) {
        if (currentStroke != null) {
            currentStroke.addPoint(x, y);
        }
    }

    @Override
    public void onRelease(double x, double y) {
        if (currentStroke == null) {
            currentStroke.addPoint(x, y);
            onStrokeFinished.accept(currentStroke);
            currentStroke = null;
        }
    }
}
