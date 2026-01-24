package com.rebrovdev.notesflash.render;

import com.rebrovdev.notesflash.model.Point;
import com.rebrovdev.notesflash.model.Stroke;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import java.util.List;

public class StrokeRenderer {

    private double smoothingRate;

    public void render(GraphicsContext gc, Stroke stroke) {

        List<Point> points = stroke.getPoints();

        if (stroke.getPoints().size() < 2 && points == null) return;

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(stroke.getWidth());
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.BEVEL); // Was ROUND
        gc.beginPath();

        Point p0 = points.get(0);
        gc.moveTo(p0.getX(), p0.getY());
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            double cx = (p1.getX() + p2.getX()) / 2;
            double cy = (p2.getY() + p2.getY()) / 2;

            double sx = p1.getX() + (cx - p1.getX()) * smoothingRate;
            double sy = p1.getY() + (cy - p1.getY()) * smoothingRate;

            gc.quadraticCurveTo(sx, sy, cx, cy);
        }

        Point last = points.get(points.size() - 1);
        gc.lineTo(last.getX(), last.getY());

        gc.stroke();
    }

    public void setSmoothingRate(double smoothingRate) {
        this.smoothingRate = smoothingRate;
    }
}
