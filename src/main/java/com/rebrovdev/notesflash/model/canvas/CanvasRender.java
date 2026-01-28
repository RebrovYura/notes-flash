package com.rebrovdev.notesflash.model.canvas;

import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.scene.canvas.GraphicsContext;

public class CanvasRender {
    public static void redraw(GraphicsContext gc, CanvasState state, Smoothing smoothing) {
        double w = gc.getCanvas().getWidth();
        double h = gc.getCanvas().getHeight();

        gc.clearRect(0, 0, w, h);

        for (Stroke stroke: state.getStrokes()) {
            smoothing.redraw(gc, stroke.getPoints());
        }
    }
}
