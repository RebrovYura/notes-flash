package com.rebrovdev.notesflash.controller;

import com.rebrovdev.notesflash.tools.ToolType;
import com.rebrovdev.notesflash.viewmodel.CanvasViewModel;
import javafx.fxml.FXML;

public class EditorController {

    private CanvasViewModel viewModel;

    public void setViewModel (CanvasViewModel viewModel) {
        this.viewModel = viewModel;
    }

//    @FXML
//    private void onPenClicked() {
//        viewModel.setActiveTool(ToolType.PEN);
//    }
//
//    @FXML
//    private void onEraserClicked() {
//        viewModel.setActiveTool(ToolType.ERASER);
//    }
}
