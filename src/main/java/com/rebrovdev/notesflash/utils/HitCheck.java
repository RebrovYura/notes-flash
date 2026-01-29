package com.rebrovdev.notesflash.utils;

import com.rebrovdev.notesflash.model.canvas.Point;
import com.rebrovdev.notesflash.model.canvas.Stroke;

import java.util.List;

public final class HitCheck {

    public static double distanceToStroke(Stroke stroke, double x, double y) {
        List<Point> points = stroke.getPoints();
        if (points.size() < 2) return Double.MAX_VALUE;

        double min = Double.MAX_VALUE;

        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i);
            Point b = points.get(i + 1);

            double d = distancePointToSegment(x, y,
                                            a.getX(), a.getY(),
                                            b.getX(), b.getY());

            if (d < min) min = d;
        }
        return min;
    }

    private static double distancePointToSegment(double px, double py,
                                                 double x1, double x2,
                                                 double y1, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (dx == 0 && dy == 0) {
            return Math.hypot(px - x1, py - y1);
        }

        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);

        t = Math.max(0, Math.min(1, t));

        double projX = x1 + t * dx;
        double projY = y1 + t * dy;

        return Math.hypot(px - projX, py - projY);
    }

    public static boolean isStrokeHit(Stroke stroke, double x, double y, double radius) {
        return distanceToStroke(stroke, x, y) <= radius;
    }
}
