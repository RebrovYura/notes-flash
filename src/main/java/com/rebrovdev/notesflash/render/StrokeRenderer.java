package com.rebrovdev.notesflash.render;

import com.rebrovdev.notesflash.model.Point;
import com.rebrovdev.notesflash.model.Stroke;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StrokeRenderer {

    public void render(GraphicsContext gc, Stroke stroke) {

        List<Point> points = stroke.getPoints();

        if (stroke == null) return;
        if (stroke.getPoints().size() < 2 && points == null) return;

//        var points = stroke.getPoints();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(stroke.getWidth());
        gc.setLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        gc.setLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        gc.beginPath();
//        gc.moveTo(points.getFirst().getX(), points.getFirst().getY());

//        for (int i = 0; i < points.size(); i++) {
//            var p = points.get(i);
//            gc.lineTo(p.getX(), p.getY());
//        }

        Point p0 = points.get(0);
        gc.moveTo(p0.getX(), p0.getY());
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            double cx = (p1.getX() + p2.getX()) / 2;
            double cy = (p2.getY() + p2.getY()) / 2;

            double sx = p1.getX() + (cx - p1.getX()) * 0.8;
            double sy = p1.getY() + (cy - p1.getY()) * 0.8;

            gc.quadraticCurveTo(sx, sy, cx, cy);
        }

        Point last = points.get(points.size() - 1);
        gc.lineTo(last.getX(), last.getY());

        gc.stroke();
    }
}
