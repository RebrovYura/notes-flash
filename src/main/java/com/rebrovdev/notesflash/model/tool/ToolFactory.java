package com.rebrovdev.notesflash.model.tool;

import com.rebrovdev.notesflash.model.canvas.CanvasState;
import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.scene.canvas.Canvas;

public class ToolFactory {

    public static Tool createPen(Canvas canvas, CanvasState state) {
        return new PenTool(canvas.getGraphicsContext2D(), new Smoothing(), state);
    }

    public static Tool createEraser(Canvas canvas, CanvasState state) {
        return new EraserTool(canvas.getGraphicsContext2D(), new Smoothing(), state);
    }
}
