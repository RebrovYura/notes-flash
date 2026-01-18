package com.rebrovdev.notesflash.viewmodel;

import com.rebrovdev.notesflash.model.Document;
import com.rebrovdev.notesflash.model.Page;
import com.rebrovdev.notesflash.model.Stroke;
import com.rebrovdev.notesflash.tools.PenTool;
import com.rebrovdev.notesflash.tools.Tool;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CanvasViewModel {
    
    private final Document document = new Document("New document");
    private final ObjectProperty<Page> currentPage = new SimpleObjectProperty<>(document.getCurrentPage());
    private final ObjectProperty<Tool> activeTool = new SimpleObjectProperty<>();

    private void addStroke(Stroke stroke) {
        currentPage.get().getStrokes().add(stroke);
        currentPage.set(currentPage.get());
    }

    public CanvasViewModel() {
        activeTool.set(new PenTool(0x000000, 3.0, this::addStroke));
    }

    public void onMousePresses(double x, double y) {
        activeTool.get().onPress(x, y);
        System.out.println("pressed");
    }

    public void onMouseDragged(double x, double y) {
        activeTool.get().onDrag(x, y);
        System.out.println("dragged");
    }

    public void onMouseReleased(double x, double y) {
        activeTool.get().onRelease(x, y);
        System.out.println("released");
    }

    public ObjectProperty<Page> currentPageProperty() {
        return currentPage;
    }
}
