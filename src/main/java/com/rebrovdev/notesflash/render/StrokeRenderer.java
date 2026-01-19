package com.rebrovdev.notesflash.render;

import com.rebrovdev.notesflash.model.Point;
import com.rebrovdev.notesflash.model.Stroke;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StrokeRenderer {

    public void render(GraphicsContext gc, Stroke stroke) {

        var points = stroke.getPoints();

        if (stroke.getPoints().size() < 2)
            return;

        gc.setStroke(Color.BLACK);

        gc.setLineWidth(stroke.getWidth());

        gc.beginPath();
        gc.moveTo(points.getFirst().getX(), points.getFirst().getY());

        for (int i = 0; i < points.size(); i++) {
            var p = points.get(i);
            gc.lineTo(p.getX(), p.getY());
        }

        gc.stroke();
    }
}
