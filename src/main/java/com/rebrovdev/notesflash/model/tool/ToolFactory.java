package com.rebrovdev.notesflash.model.tool;

import com.rebrovdev.notesflash.model.canvas.CanvasState;
import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.scene.canvas.Canvas;

public class ToolFactory {

    private static CanvasState state;

    public static Tool createPen(Canvas canvas) {
        return new PenTool(canvas.getGraphicsContext2D(), new Smoothing(), state);
    }

    public static Tool createEraser(Canvas canvas) {
        return new EraserTool(canvas.getGraphicsContext2D(), new Smoothing(), state);
    }
}
