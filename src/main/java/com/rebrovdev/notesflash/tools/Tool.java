package com.rebrovdev.notesflash.tools;

public interface Tool {
    void onPress(double x, double y);
    void onDrag(double x, double y);
    void onRelease(double x, double y);
}
