package com.rebrovdev.notesflash.tools;

import com.rebrovdev.notesflash.model.Stroke;

import java.util.function.Consumer;

public class PenTool implements Tool {

    private final Consumer<Stroke> onStrokeFinished;
    private Stroke currentStroke;
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
        if (currentStroke != null) {
            currentStroke.addPoint(x, y);
            onStrokeFinished.accept(currentStroke);
//            currentStroke = null;
        }
    }

    public boolean hasPreview() {
        return currentStroke != null;
    }

    public Stroke getPreview() {
        return currentStroke;
    }

    public Stroke finishStroke() {
        Stroke finish = currentStroke;
        currentStroke = null;
        return finish;
    }
}
