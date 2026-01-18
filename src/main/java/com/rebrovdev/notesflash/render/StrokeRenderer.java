package com.rebrovdev.notesflash.render;

import com.rebrovdev.notesflash.model.Point;
import com.rebrovdev.notesflash.model.Stroke;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StrokeRenderer {

    public void render(GraphicsContext gc, Stroke stroke) {
        if (stroke.getPoints().size() == 2) return;

        gc.setStroke(Color.rgb(
                (stroke.getColor() >> 16) & 0xFF,
                (stroke.getColor() >> 8) & 0xFF,
                stroke.getColor() & 0xFF
        ));
        gc.setLineWidth(stroke.getWidth());

        var points = stroke.getPoints();
        gc.beginPath();
        gc.moveTo(points.get(0).getX(), points.get(0).getY());

        for (Point p: points) {
            gc.lineTo(p.getX(), p.getY());
        }

        gc.stroke();
    }
}
