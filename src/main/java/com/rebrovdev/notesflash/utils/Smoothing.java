package com.rebrovdev.notesflash.utils;

import com.rebrovdev.notesflash.model.canvas.Point;
import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public class Smoothing {

    public void redraw(GraphicsContext gc, List<Point> points) {
        if (points.size() < 3) return;

        Point p0 = points.get(points.size() - 3);
        Point p1 = points.get(points.size() - 2);
        Point p2 = points.get(points.size() - 1);

        Point mid1 = midpoint(p0, p1);
        Point mid2 = midpoint(p1, p2);

        gc.beginPath();
        gc.moveTo(mid1.getX(), mid1.getY());
        gc.quadraticCurveTo(p1.getX(), p1.getY(), mid2.getX(), mid2.getY());
        gc.stroke();

    }

    public Point midpoint(Point a, Point b) {
        return new Point(
                (a.getX() + b.getX()) / 2,
                (a.getY() + b.getY()) / 2
        );
    }
}
