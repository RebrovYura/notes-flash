package com.rebrovdev.notesflash.model.tool;

import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.scene.canvas.Canvas;

public class ToolFactory {

    public static Tool createPen(Canvas canvas) {
        return new PenTool(canvas.getGraphicsContext2D(), new Smoothing());
    }
}
