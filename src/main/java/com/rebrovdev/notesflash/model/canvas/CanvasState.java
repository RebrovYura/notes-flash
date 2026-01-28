package com.rebrovdev.notesflash.model.canvas;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final List<Stroke> strokes = new ArrayList<>();

    public void addStroke(Stroke stroke) {
        strokes.add(stroke);
    }

    public void removeStroke(Stroke stroke) {
        strokes.remove(stroke);
    }

    public List<Stroke> getStrokes() {
        return strokes;
    }
}
