package com.rebrovdev.notesflash.tools;

import javafx.scene.canvas.GraphicsContext;

public class PenTool implements Tool {

    @Override
    public void onPress(GraphicsContext gc, double x, double y) {
        gc.beginPath();
        gc.moveTo(x, y);
        gc.stroke();
    }

    @Override
    public void onDrag(GraphicsContext gc, double x, double y) {
        gc.lineTo(x, y);
        gc.stroke();
    }

    @Override
    public void onRelease(GraphicsContext gc, double x, double y) {
        gc.closePath();
    }
}
