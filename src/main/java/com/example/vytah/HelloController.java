package com.example.vytah;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane root;
    private Elevator ele;

    @FXML private void draw(){
        if(ele != null) return;
        ele = new Elevator();
        root.getChildren().add(ele);

        root.getScene().setOnKeyPressed(e -> ele.options(e.getCode()));


    }
}
