package com.rebrovdev.notesflash.render;

import com.rebrovdev.notesflash.model.Page;
import com.rebrovdev.notesflash.model.Stroke;
import javafx.scene.canvas.GraphicsContext;

public class PageRenderer {

    private final StrokeRenderer strokeRenderer = new StrokeRenderer();
    public void render(GraphicsContext gc, Page page) {
        if (page == null) return;
        strokeRenderer.setSmoothingRate(0.6);
        for (Stroke stroke: page.getStrokes()) {
            strokeRenderer.render(gc, stroke);
        }
    }
}
