package com.rebrovdev.notesflash.tools;

import com.rebrovdev.notesflash.model.Point;
import com.rebrovdev.notesflash.model.Stroke;

import java.util.function.Consumer;

public class PenTool implements Tool {

    private final Consumer<Stroke> onStrokeFinished;
    private Stroke currentStroke;
    private Point lastPoint;
    private int color;
    private double width;

    private double lastX;
    private double lastY;
    private boolean firstPoint = true;

    private static final double ALPHA = 0.4;

    public PenTool(int color, double width, Consumer<Stroke> onStrokeFinished) {
        this.color = color;
        this.width = width;
        this.onStrokeFinished = onStrokeFinished;
        this.currentStroke = null;
    }

    @Override
    public void onPress(double x, double y) {
//        currentStroke = null;
        currentStroke = new Stroke(color, width);
        firstPoint = true;
//        currentStroke.addPoint(x, y);
        addSmoothedPoint(x, y);
    }

    @Override
    public void onDrag(double x, double y) {
//        if (currentStroke != null) {
//            currentStroke.addPoint(x, y);
//        }
        addSmoothedPoint(x, y);
//        if (lastPoint == null && currentStroke != null) {
//            lastPoint = new Point(x, y);
//            currentStroke.addPoint(x, y);
//            return;
//        }
//        double smoothX = (lastPoint.getX() + x) / 2;
//        double smoothY = (lastPoint.getY() + y) / 2;
//        currentStroke.addPoint(smoothX, smoothY);
//        lastPoint = new Point(smoothX, smoothY);
    }

    @Override
    public void onRelease(double x, double y) {
        if (currentStroke != null) {
            currentStroke.addPoint(x, y);
            addSmoothedPoint(x,y);
            onStrokeFinished.accept(currentStroke);
//            currentStroke = null;
        }
//        currentStroke = null;
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

    private void addSmoothedPoint(double x, double y) {

        if (firstPoint) {
            lastX = x;
            lastY = y;
            firstPoint = false;
        }

        double smoothX = lastX + (x - lastX) * ALPHA;
        double smoothY = lastY + (y - lastY) * ALPHA;
        Point currentPoint = new Point(smoothX, smoothY);
        currentStroke.addPoint(currentPoint.getX(), currentPoint.getY());

        lastX = smoothX;
        lastY = smoothY;
    }
}
