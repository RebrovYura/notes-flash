package com.rebrovdev.notesflash.model;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final List<Stroke> strokes = new ArrayList<>();
    private PageBackgroundType background = PageBackgroundType.PLAIN;

    public List<Stroke> getStrokes() {
        return strokes;
    }

    public PageBackgroundType getBackground() {
        return background;
    }

    public void setBackground(PageBackgroundType background) {
        this.background = background;
    }
}
