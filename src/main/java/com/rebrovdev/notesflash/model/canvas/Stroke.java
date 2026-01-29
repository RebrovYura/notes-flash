package com.rebrovdev.notesflash.model.canvas;

import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Stroke {

    private final List<Point> points = new ArrayList<>();
    private final Smoothing smoothing;

    public Stroke(Smoothing smoothing) {
        this.smoothing = smoothing;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints(){
        return points;
    }

    public void redraw(GraphicsContext gc, List<Point> points) {
        smoothing.redraw(gc, points);
    }
}
