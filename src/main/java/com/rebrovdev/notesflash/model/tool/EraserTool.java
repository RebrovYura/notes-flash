package com.rebrovdev.notesflash.model.tool;

import com.rebrovdev.notesflash.model.canvas.CanvasState;
import com.rebrovdev.notesflash.model.canvas.Point;
import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

import java.util.ArrayList;
import java.util.List;

public class EraserTool implements Tool {

    private final List<Point> points = new ArrayList<>();
    private final GraphicsContext gc;
    private Smoothing smoothing;
    private CanvasState state;
    private final double radius = 15;

    public EraserTool(GraphicsContext gc, Smoothing smoothing, CanvasState state) {
        this.gc = gc;
        this.smoothing = smoothing;
        this.state = state;
        setup();
    }

    @Override
    public void setup() {
    }

    @Override
    public void onPress(double x, double y) {
    }

    @Override
    public void onDrag(double x, double y) {
        state.getStrokes().removeIf(stroke ->
                stroke.getPoints().stream().anyMatch(p ->
                        distance(p, x, y) < radius
                )

        );
    }

    @Override
    public void onRelease(double x, double y) {
    }

    private double distance(Point p, double x, double y) {
        return Math.hypot(p.getX() - x, p.getY() - y);
    }
}
