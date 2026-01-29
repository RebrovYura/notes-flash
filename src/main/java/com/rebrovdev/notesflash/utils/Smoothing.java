package com.rebrovdev.notesflash.utils;

import com.rebrovdev.notesflash.model.canvas.Point;
import javafx.scene.canvas.GraphicsContext;
import java.util.List;

public class Smoothing {

    public void redraw(GraphicsContext gc, List<Point> points) {

        if (points.size() < 2) return;

        gc.beginPath();
        gc.moveTo(points.get(0).getX(), points.get(0).getY());

        for (int i = 1; i < points.size() - 1; i++) {
            Point p0 = points.get(i);
            Point p1 = points.get(i + 1);

            double cx = (p0.getX() + p1.getX()) / 2;
            double cy = (p0.getY() + p1.getY()) / 2;

            gc.quadraticCurveTo(
                    p0.getX(), p0.getY(),
                    cx, cy
            );
        }

        gc.stroke();

    }

    public Point midpoint(Point a, Point b) {
        return new Point(
                (a.getX() + b.getX()) / 2,
                (a.getY() + b.getY()) / 2
        );
    }
}
